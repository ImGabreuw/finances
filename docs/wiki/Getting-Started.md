# Início Rápido

## Pré-requisitos

Antes de começar, certifique-se de ter instalado:

| Ferramenta | Versão Mínima | Verificação |
|------------|---------------|-------------|
| **Java** | 17+ | `java -version` |
| **Maven** | 3.8+ | `mvn -version` |
| **Node.js** | 18+ | `node -v` |
| **npm** | 9+ | `npm -v` |
| **Docker** (opcional) | 20+ | `docker -v` |

---

## Clonando o Repositório

```bash
git clone https://github.com/ImGabreuw/finances.git
cd finances
```

A estrutura do projeto é organizada da seguinte forma:

```
finances/
├── finances-backend/         # API Spring Boot
├── finances-data-provider/   # Serviço de scraping
├── finances-web/             # Interface Next.js
└── docs/                     # Documentação
```

---

## Configuração dos Módulos

### 1. Backend (Java/Spring Boot)

O backend utiliza o profile `dev` por padrão, que configura um banco H2 em memória.

```bash
cd finances-backend

# Usando Maven Wrapper (recomendado)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Ou usando Maven instalado
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

**Serviços disponíveis:**
- API REST: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User: `sa`
  - Password: (vazio)

### 2. Data Provider (Node.js/Express)

```bash
cd finances-data-provider

# Instalar dependências
npm install

# Copiar variáveis de ambiente
cp .env.example .env

# Iniciar em modo desenvolvimento
npm run dev
```

**Serviço disponível:**
- API REST: http://localhost:3000

> [!WARNING]
> O Puppeteer irá baixar automaticamente o Chromium na primeira execução. Isso pode levar alguns minutos.

### 3. Web (Next.js)

```bash
cd finances-web

# Instalar dependências
npm install

# Iniciar em modo desenvolvimento
npm run dev
```

**Serviço disponível:**
- Interface web: http://localhost:3000

> [!TIP]
> Como o Data Provider também usa a porta 3000, você precisará alterar a porta do Next.js ou executar os serviços em ordem diferente.

Para alterar a porta do Next.js:
```bash
npm run dev -- -p 3001
```

---

## Ordem de Inicialização Recomendada

Para garantir que todos os serviços funcionem corretamente:

```mermaid
flowchart LR
    1[1. Data Provider<br/>:3000] --> 2[2. Backend<br/>:8080] --> 3[3. Web<br/>:3001]
```

1. **Primeiro:** Data Provider (porta 3000)
2. **Segundo:** Backend (porta 8080)
3. **Terceiro:** Web (porta 3001)

---

## Execução com Docker

### Backend

```bash
cd finances-backend

# Build da imagem
docker build -t finances-backend .

# Executar container
docker run -p 8080:8080 finances-backend
```

### Docker Compose (futuro)

> [!IMPORTANT]
> Um arquivo `docker-compose.yml` está planejado para facilitar a orquestração de todos os serviços.

---

## Verificando a Instalação

### Testando o Data Provider

```bash
# Consultar dados de uma ação
curl http://localhost:3000/stock/PETR4

# Consultar dados de um FII
curl http://localhost:3000/real-state-fund/HGLG11

# Consultar preço atual
curl http://localhost:3000/currentPrice/VALE3
```

### Testando o Backend

```bash
# Verificar health check
curl http://localhost:8080/actuator/health
```

---

## Variáveis de Ambiente

### Backend (`application-dev.yaml`)

| Variável | Padrão | Descrição |
|----------|--------|-----------|
| `spring.datasource.url` | `jdbc:h2:mem:testdb` | URL do banco de dados |
| `finances-data-provider.base-url` | `http://localhost:3000` | URL do Data Provider |

### Data Provider (`.env`)

| Variável | Padrão | Descrição |
|----------|--------|-----------|
| `PORT` | `3000` | Porta do servidor Express |

---

## Comandos Úteis

### Backend

| Comando | Descrição |
|---------|-----------|
| `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev` | Inicia em modo dev |
| `./mvnw test` | Executa testes |
| `./mvnw package` | Gera o JAR |
| `./mvnw clean compile` | Limpa e compila |

### Data Provider

| Comando | Descrição |
|---------|-----------|
| `npm run dev` | Inicia em modo dev (hot reload) |
| `npm test` | Executa testes com Jest |

### Web

| Comando | Descrição |
|---------|-----------|
| `npm run dev` | Inicia em modo dev |
| `npm run build` | Build de produção |
| `npm run lint` | Executa ESLint |
| `npm start` | Inicia build de produção |

---

## Resolução de Problemas

### Erro: "Port already in use"

```bash
# Encontrar processo usando a porta
lsof -i :3000  # ou :8080

# Matar o processo
kill -9 <PID>
```

### Erro: Puppeteer não encontra Chromium

```bash
# No Linux, instalar dependências do Chromium
sudo apt-get install -y \
  libnss3 \
  libatk1.0-0 \
  libatk-bridge2.0-0 \
  libcups2 \
  libdrm2 \
  libxkbcommon0 \
  libxcomposite1 \
  libxdamage1 \
  libxrandr2 \
  libgbm1 \
  libasound2
```

### Erro: Java 17 não encontrado

```bash
# Ubuntu/Debian
sudo apt install openjdk-17-jdk

# Definir JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
```

---

## Próximos Passos

Agora que o ambiente está configurado, explore:

- [Arquitetura](./Architecture.md) - Entenda como o sistema funciona
- [Backend Module](./Backend-Module.md) - Detalhes do módulo Spring Boot
- [Data Provider Module](./Data-Provider-Module.md) - Como funciona o scraping
- [Contribuindo](./Contributing.md) - Como contribuir com o projeto

---

[Voltar para Home](./Home.md)
