# Backend Module

O módulo `finances-backend` é o coração do sistema, responsável por toda a lógica de negócio, persistência de dados e integração com o Data Provider.

---

## Stack Tecnológica

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 17 | Linguagem principal |
| **Spring Boot** | 3.4.4 | Framework base |
| **Spring Data JPA** | - | Persistência de dados |
| **Spring Validation** | - | Validação de beans |
| **Spring Mail** | - | Envio de emails |
| **Spring Actuator** | - | Métricas e health checks |
| **H2** | - | Banco de dados (dev) |
| **PostgreSQL** | - | Banco de dados (prod) |
| **Lombok** | - | Redução de boilerplate |
| **OpenCSV** | 5.9 | Importação de dados CSV |
| **JavaFaker** | 1.0.2 | Geração de dados fake |

---

## Estrutura de Pacotes

O backend segue os princípios da **Clean Architecture**, separando as responsabilidades em camadas:

```
br.com.gabreuw.finances/
├── FinancesApplication.java      # Entry point
│
├── announcement_search_engine/   # Motor de busca de comunicados
├── asset_analyzer/               # Análise de ativos
├── bank_account/                 # Gestão de contas bancárias
├── interest/                     # Cálculos de juros
├── investment_portfolio/         # Portfólio de investimentos
├── transactions/                 # Transações
│   ├── fixed/income/            #    └── Renda fixa
│   └── variable/income/         #    └── Renda variável
└── shared/                       # Utilitários compartilhados
```

---

## Módulos Funcionais

### Announcement Search Engine

Este módulo é responsável por buscar, armazenar e notificar comunicados de ativos (dividendos, fatos relevantes, etc.).

#### Estrutura

```
announcement_search_engine/
├── application/
│   ├── config/
│   │   ├── bean/
│   │   │   ├── FakerConfig.java         # Bean do JavaFaker
│   │   │   └── RestTemplateConfig.java  # Cliente HTTP
│   │   └── database/
│   │       └── DatabaseSeeder.java      # Seeds iniciais
│   ├── database/
│   │   ├── entities/
│   │   │   └── AnnouncementEntity.java  # Entidade JPA
│   │   ├── jpa/
│   │   │   └── AnnouncementJpaRepository.java
│   │   └── repository/
│   │       └── AnnouncementRepositoryImpl.java
│   ├── mapper/
│   │   └── AnnouncementMapperImpl.java
│   ├── provider/
│   │   └── FinancesDataProvider.java    # Integração HTTP
│   └── web/
│       └── dto/
│           └── AnnouncementDTO.java
└── domain/
    ├── adapters/
    │   ├── mapper/
    │   │   └── AnnouncementMapper.java  # Interface
    │   ├── provider/
    │   │   └── AnnouncementProvider.java
    │   └── repository/
    │       └── AnnouncementRepository.java
    ├── entities/
    │   ├── Announcement.java            # Domínio
    │   ├── enums/
    │   │   └── NotificationStatus.java
    │   └── helper/
    │       └── AnnouncementHelper.java
    ├── errors/
    │   ├── MissingEnviromentVariableException.java
    │   └── NotFoundAnnouncementsException.java
    └── usecases/
        ├── GetUnnotifiedAnnouncementsUseCase.java
        ├── NotifyLastAnnouncementUseCase.java
        └── NotifyUserAssetsAnnouncementsUseCase.java
```

#### Entidade: Announcement

```java
@Entity
@Data
@Builder
public class AnnouncementEntity implements SelfValidation<AnnouncementEntity> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String assetCode;      // Ex: "PETR4", "HGLG11"

    @NotBlank
    private String title;          // Título do comunicado

    @NotNull
    private LocalDate releaseDate; // Data de publicação

    @NotBlank
    @URL
    private String downloadUrl;    // Link para PDF

    @Enumerated(STRING)
    private NotificationStatus notificationStatus; // PENDING, NOTIFIED, ERROR
}
```

#### Casos de Uso

| Use Case | Descrição |
|----------|-----------|
| `NotifyLastAnnouncementUseCase` | Busca e notifica o último comunicado de um ativo |
| `NotifyUserAssetsAnnouncementsUseCase` | Notifica comunicados de todos os ativos do portfólio |
| `GetUnnotifiedAnnouncementsUseCase` | Lista comunicados pendentes de notificação |

---

### Transactions

Gerencia transações de compra/venda de ativos de renda fixa e variável.

#### Renda Fixa (Fixed Income)

```
transactions/fixed/income/
├── application/
│   └── database/
│       └── entities/
│           └── FixedIncomeTransactionEntity.java
└── domain/
    └── entities/
        └── enums/
            ├── AssetType.java  # CDB, LCI, LCA, TESOURO_DIRETO...
            └── Indexer.java    # CDI, IPCA, SELIC, PREFIXADO
```

**Campos da entidade:**

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `assetType` | `AssetType` | Tipo do ativo (CDB, LCI, etc.) |
| `issuingAgency` | `String` | Instituição emissora |
| `profitabilityInPercent` | `Double` | Rentabilidade (ex: 110% CDI) |
| `indexer` | `Indexer` | Indexador (CDI, IPCA, etc.) |
| `liquidity` | `String` | Liquidez (D+0, D+30, etc.) |
| `expiresIn` | `LocalDate` | Data de vencimento |
| `totalInvested` | `Double` | Valor investido |
| `units` | `Double` | Quantidade de cotas |
| `operationDate` | `LocalDate` | Data da operação |

#### Renda Variável (Variable Income)

```
transactions/variable/income/
├── application/
│   └── database/
│       └── entities/
│           └── VariableIncomeTransactionEntity.java
└── domain/
    └── entities/
        └── enums/
            ├── AssetType.java     # ACAO, FII, ETF, BDR
            └── OperationType.java # COMPRA, VENDA
```

**Campos da entidade:**

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `assetCode` | `String` | Código do ativo (PETR4, VALE3) |
| `assetType` | `AssetType` | Tipo (Ação, FII, ETF, BDR) |
| `operationDate` | `LocalDate` | Data da operação |
| `operationType` | `OperationType` | Compra ou Venda |
| `operationAverageCost` | `Double` | Preço médio |
| `numberOfShares` | `Integer` | Quantidade de ações |
| `totalInvested` | `Double` | Valor total |

---

### Investment Portfolio

Calcula a posição consolidada do portfólio do usuário.

**Use Case principal:**

```java
@Component
public record CalculateVariableIncomePositionUseCase(
    /* dependencies */
) implements UseCase<InputValues, OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        // Consolida transações por ativo
        // Calcula preço médio ponderado
        // Retorna lista de VariableIncomeAsset
    }
}
```

---

### Shared

Utilitários e componentes compartilhados entre todos os módulos.

```
shared/
├── errors/
│   └── BaseException.java       # Exceção base
├── helper/
│   ├── CsvHelper.java           # Utilitários para CSV
│   └── DateHelper.java          # Formatação de datas
├── logging/
│   └── LoggingAspect.java       # Logging com AOP
├── pagination/
│   └── PageResponse.java        # DTO de paginação
├── usecase/
│   └── UseCase.java             # Interface base
└── validation/
    └── SelfValidation.java      # Interface de auto-validação
```

#### Interface UseCase

```java
public interface UseCase<I extends InputValues, O extends OutputValues> {
    O execute(I input);

    interface InputValues {}
    interface OutputValues {}
}
```

#### Interface SelfValidation

```java
public interface SelfValidation<T> {
    default void validate(T entity) {
        var factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        var violations = validator.validate(entity);
        
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
```

---

## Configuração

### Profiles

| Profile | Banco de Dados | Uso |
|---------|----------------|-----|
| `dev` | H2 (memória) | Desenvolvimento local |
| `test` | H2 (memória) | Testes automatizados |
| `prod` | PostgreSQL | Produção |

### application-dev.yaml

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    username: sa
    password:
    driver-class-name: org.h2.Driver

  h2:
    console:
      enabled: true
      path: /h2-console

finances-data-provider:
  base-url: http://localhost:3000
  endpoints:
    announcements: ${finances-data-provider.base-url}/announcements
    real-state-fund: ${finances-data-provider.base-url}/real-state-fund
    stock: ${finances-data-provider.base-url}/stock
```

### Logging

```yaml
logging:
  level:
    root: INFO
    br.com.gabreuw.finances: DEBUG
```

---

## Docker

### Dockerfile

O projeto utiliza multi-stage build para otimização:

```dockerfile
# Stage 1: Dependências
FROM maven:3.9-eclipse-temurin-17-alpine AS dependencies
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Stage 2: Build
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY --from=dependencies /root/.m2 /root/.m2
COPY src ./src
COPY pom.xml .
RUN mvn package -DskipTests

# Stage 3: Runtime
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"
RUN addgroup --system java && adduser --system java --ingroup java
USER java
COPY --from=build /app/target/finances-*.jar app.jar
EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -jar app.jar -Dspring.profiles.active=dev
```

### Comandos

```bash
# Build
docker build -t finances-backend .

# Executar
docker run -p 8080:8080 finances-backend

# Produção
docker build -f Dockerfile.prod -t finances-backend:prod .
```

---

## Testes

### Executando Testes

```bash
# Todos os testes
./mvnw test

# Testes específicos
./mvnw test -Dtest=AnnouncementRepositoryTest

# Com relatório de cobertura
./mvnw test jacoco:report
```

### Estrutura de Testes

```
src/test/java/
└── br/com/gabreuw/finances/
    ├── announcement_search_engine/
    │   └── domain/
    │       └── usecases/
    │           └── NotifyLastAnnouncementUseCaseTest.java
    └── transactions/
        └── ...
```

---

## Próximos Passos

- [Data Provider Module](./Data-Provider-Module.md) - Como funciona o scraping
- [Web Module](./Web-Module.md) - Interface do usuário
- [Arquitetura](./Architecture.md) - Visão geral do sistema

---

[Voltar para Home](./Home.md)
