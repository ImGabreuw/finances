# Arquitetura

Esta página descreve a arquitetura geral do sistema Finances, incluindo a comunicação entre módulos, padrões utilizados e estrutura interna de cada componente.

## Visão Geral

O Finances segue uma arquitetura de **microsserviços simplificada**, onde cada módulo tem responsabilidades bem definidas:

```mermaid
flowchart TB
    subgraph CLIENT["Cliente"]
        BROWSER[Navegador]
    end

    subgraph FRONTEND["Frontend Layer"]
        NEXTJS[finances-web<br/>Next.js 14<br/>:3001]
    end

    subgraph BACKEND["Backend Layer"]
        SPRING[finances-backend<br/>Spring Boot 3.4<br/>:8080]
        DB[(H2 / PostgreSQL)]
    end

    subgraph SCRAPING["Data Layer"]
        PROVIDER[finances-data-provider<br/>Express + Puppeteer<br/>:3000]
    end

    subgraph EXTERNAL["Fontes Externas"]
        SI[Status Invest]
        FD[Fundamentus]
        GF[Google Finance]
        RA[Reclame Aqui]
    end

    BROWSER --> NEXTJS
    NEXTJS --> |REST API| SPRING
    SPRING --> |JPA| DB
    SPRING --> |HTTP Client| PROVIDER
    PROVIDER --> |Web Scraping| SI
    PROVIDER --> |Web Scraping| FD
    PROVIDER --> |Web Scraping| GF
    PROVIDER --> |Web Scraping| RA

    style NEXTJS fill:#000000,color:#fff
    style SPRING fill:#6DB33F,color:#fff
    style PROVIDER fill:#3178C6,color:#fff
    style DB fill:#336791,color:#fff
```

## Fluxo de Dados

### Consulta de Dados de Ativos

```mermaid
sequenceDiagram
    participant U as Usuário
    participant W as Web (Next.js)
    participant B as Backend (Spring)
    participant P as Data Provider
    participant S as Status Invest

    U->>W: Solicita dados do ativo PETR4
    W->>B: GET /api/assets/PETR4
    B->>P: GET /stock/PETR4
    P->>S: Scraping da página
    S-->>P: HTML com dados
    P-->>B: JSON com indicadores
    B-->>W: Dados processados
    W-->>U: Exibe dashboard
```

### Notificação de Comunicados

```mermaid
sequenceDiagram
    participant S as Scheduler
    participant B as Backend
    participant P as Data Provider
    participant DB as Database
    participant E as Email Service

    S->>B: Trigger job (periódico)
    B->>DB: Lista ativos do portfólio
    DB-->>B: PETR4, VALE3, HGLG11...
    
    loop Para cada ativo
        B->>P: GET /announcements/{code}
        P-->>B: Último comunicado
        B->>DB: Verifica se já notificou
        alt Novo comunicado
            B->>DB: Salva comunicado
            B->>E: Envia notificação
        end
    end
```

## Arquitetura do Backend

O módulo `finances-backend` segue os princípios da **Clean Architecture** adaptada para Spring Boot:

```mermaid
graph TB
    subgraph "Application Layer"
        WEB[Web Controllers]
        CONFIG[Configurations]
        MAPPER[Mappers]
        ENTITIES[JPA Entities]
        REPO_IMPL[Repository Implementations]
    end

    subgraph "Domain Layer"
        DOMAIN_ENTITIES[Domain Entities]
        USECASES[Use Cases]
        ADAPTERS[Adapters/Ports]
    end

    WEB --> USECASES
    USECASES --> ADAPTERS
    REPO_IMPL --> ADAPTERS
    MAPPER --> DOMAIN_ENTITIES
    MAPPER --> ENTITIES

    style USECASES fill:#FFD700,color:#000
    style DOMAIN_ENTITIES fill:#FFD700,color:#000
    style ADAPTERS fill:#FFD700,color:#000
```

### Estrutura de Pacotes

```
br.com.gabreuw.finances/
├── announcement_search_engine/    # Motor de busca de comunicados
│   ├── application/
│   │   ├── config/               # Beans e configurações
│   │   ├── database/             # Entidades JPA e repositórios
│   │   ├── mapper/               # Conversão domain ↔ entity
│   │   ├── provider/             # Integração com Data Provider
│   │   └── web/                  # DTOs de API
│   └── domain/
│       ├── adapters/             # Interfaces (ports)
│       ├── entities/             # Entidades de domínio
│       ├── errors/               # Exceções de negócio
│       └── usecases/             # Casos de uso
├── asset_analyzer/               # Análise de ativos
├── bank_account/                 # Gestão de contas bancárias
├── interest/                     # Cálculos de juros
├── investment_portfolio/         # Portfólio de investimentos
├── transactions/                 # Transações
│   ├── fixed/income/            # Renda fixa
│   └── variable/income/         # Renda variável
└── shared/                       # Utilitários compartilhados
    ├── errors/                   # Exceções base
    ├── helper/                   # Classes auxiliares
    ├── logging/                  # Configuração de logs
    ├── pagination/               # Paginação
    ├── usecase/                  # Interface base UseCase
    └── validation/               # Self-validation
```

### Padrão UseCase

Todos os casos de uso seguem uma interface padronizada:

```java
public interface UseCase<I extends InputValues, O extends OutputValues> {
    O execute(I input);
    
    interface InputValues {}
    interface OutputValues {}
}
```

**Exemplo de implementação:**

```java
@Component
public record NotifyLastAnnouncementUseCase(
        AnnouncementProvider announcementProvider,
        AnnouncementRepository announcementRepository
) implements UseCase<InputValues, OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var announcement = announcementProvider.getLastAnnouncement(input.assetCode());
        announcementRepository.save(announcement);
        return new OutputValues(announcement);
    }

    public record InputValues(String assetCode) implements UseCase.InputValues {}
    public record OutputValues(Announcement announcement) implements UseCase.OutputValues {}
}
```

## Arquitetura do Data Provider

O módulo `finances-data-provider` utiliza o padrão **Facade** para simplificar a integração com múltiplas fontes:

```mermaid
graph LR
    subgraph "API Layer"
        ROUTES[Express Routes]
    end

    subgraph "Facade Layer"
        SF[StockFacade]
        RF[RealStateFundFacade]
    end

    subgraph "Gateway Layer"
        SI_API[StatusInvestStockAPI]
        FD_API[FundamentusStockAPI]
        RSF_API[RealStateFundAPI]
        RA_API[ReclameAquiAPI]
    end

    subgraph "Service Layer"
        STEPS[StepsService]
        XPATH[XPathService]
    end

    ROUTES --> SF
    ROUTES --> RF
    SF --> SI_API
    SF --> FD_API
    RF --> RSF_API
    RF --> RA_API
    SI_API --> STEPS
    SI_API --> XPATH

    style SF fill:#3178C6,color:#fff
    style RF fill:#3178C6,color:#fff
```

### Estrutura de Diretórios

```
src/
├── domain/
│   ├── adapters/                 # Interfaces base (DTO)
│   └── types/                    # Tipos TypeScript
├── gateways/
│   ├── google/                   # Google Finance API
│   └── status_invest/            # Status Invest scraper
├── stocks/
│   ├── facade/                   # StockFacade
│   ├── gateway/                  # APIs de ações
│   └── service/                  # Steps e XPath services
├── real-state-fund/
│   ├── facade/                   # RealStateFundFacade
│   ├── gateway/                  # APIs de FIIs
│   └── services/                 # Steps e XPath services
├── reclame-aqui/                 # Scraper do Reclame Aqui
├── announcement-search-engine/   # Busca de comunicados
├── shared/
│   ├── errors/                   # Classes de erro
│   ├── helpers/                  # Funções utilitárias
│   ├── validation/               # Validação com Zod
│   └── web/                      # HTTP status codes
├── routes.ts                     # Definição de rotas
└── server.ts                     # Entry point
```

## Padrões e Boas Práticas

### Backend

| Padrão | Uso |
|--------|-----|
| **Clean Architecture** | Separação entre domain e application layers |
| **Repository Pattern** | Abstração do acesso a dados |
| **Use Case Pattern** | Encapsulamento de regras de negócio |
| **Self-Validation** | Entidades validam a si mesmas na construção |
| **Record Classes** | DTOs e Value Objects imutáveis (Java 17+) |

### Data Provider

| Padrão | Uso |
|--------|-----|
| **Facade Pattern** | Simplifica integração com múltiplas fontes |
| **Gateway Pattern** | Encapsula comunicação com APIs externas |
| **Strategy Pattern** | Diferentes estratégias de scraping por fonte |
| **DTO Pattern** | Tipagem forte dos dados extraídos |

---

## Comunicação entre Módulos

```mermaid
graph LR
    subgraph "Backend :8080"
        FDP[FinancesDataProvider]
    end

    subgraph "Data Provider :3000"
        RT[/announcements/:code]
        RSF[/real-state-fund/:code]
        STK[/stock/:code]
        CP[/currentPrice/:code]
    end

    FDP --> |HTTP GET| RT
    FDP --> |HTTP GET| RSF
    FDP --> |HTTP GET| STK
    FDP --> |HTTP GET| CP

    style FDP fill:#6DB33F,color:#fff
```

**Configuração no Backend (`application-dev.yaml`):**

```yaml
finances-data-provider:
  base-url: http://localhost:3000
  endpoints:
    announcements: ${finances-data-provider.base-url}/announcements
    real-state-fund: ${finances-data-provider.base-url}/real-state-fund
    stock: ${finances-data-provider.base-url}/stock
```

---

## Próximos Passos

- [Voltar para Home](./Home.md)
- [Início Rápido](./Getting-Started.md)
- [Backend Module](./Backend-Module.md)
