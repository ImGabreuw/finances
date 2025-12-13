# Finances

<div align="center">

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-4.7-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Next.js](https://img.shields.io/badge/Next.js-14-000000?style=for-the-badge&logo=next.js&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**Sistema de gerenciamento de finanças pessoais e investimentos**

[Arquitetura](./Architecture.md) •
[Início Rápido](./Getting-Started.md) •
[Contribuir](./Contributing.md)

</div>

---

## Sobre o Projeto

**Finances** é uma plataforma open-source para gerenciamento de finanças pessoais focada em investidores brasileiros. O sistema permite:

- **Acompanhamento de portfólio** de renda fixa e variável
- **Notificações automáticas** de comunicados de ativos (dividendos, fatos relevantes)
- **Coleta de dados** de indicadores fundamentalistas de ações e FIIs
- **Registro de transações** de compra e venda de ativos

---

## Módulos

O projeto é organizado como um **monorepo** com três módulos principais:

| Módulo | Descrição | Documentação |
|--------|-----------|--------------|
| [**finances-backend**](./Backend-Module.md) | API REST para gestão de transações, portfólios e notificações | [Ver mais →](./Backend-Module.md) |
| [**finances-data-provider**](./Data-Provider-Module.md) | Serviço de scraping para coleta de dados de ativos | [Ver mais →](./Data-Provider-Module.md) |
| [**finances-web**](./Web-Module.md) | Interface web moderna com suporte a dark mode | [Ver mais →](./Web-Module.md) |

---

## Stack Tecnológica

### Backend
- **Java 17** com **Spring Boot 3.4.4**
- **Spring Data JPA** para persistência
- **H2** (desenvolvimento) / **PostgreSQL** (produção)
- **Lombok** para redução de boilerplate
- **Bean Validation** para validação de dados

### Data Provider
- **Node.js** com **TypeScript**
- **Express** como framework web
- **Puppeteer** para web scraping
- **Zod** para validação de schemas

### Web
- **Next.js 14** com App Router
- **React 18** com Server Components
- **Tailwind CSS** para estilização
- **Shadcn UI** para componentes
- **next-themes** para dark/light mode

---

## Documentação

| Página | Descrição |
|--------|-----------|
| [Arquitetura](./Architecture.md) | Visão geral da arquitetura, padrões e diagramas |
| [Início Rápido](./Getting-Started.md) | Como configurar e executar o projeto localmente |
| [Backend Module](./Backend-Module.md) | Detalhes do módulo Spring Boot |
| [Data Provider Module](./Data-Provider-Module.md) | Detalhes do serviço de scraping |
| [Web Module](./Web-Module.md) | Detalhes da interface Next.js |
| [Contribuindo](./Contributing.md) | Como contribuir com o projeto |

---

## Início Rápido

```bash
# Clone o repositório
git clone https://github.com/ImGabreuw/finances.git
cd finances

# Backend (porta 8080)
cd finances-backend
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Data Provider (porta 3000)
cd finances-data-provider
npm install && npm run dev

# Web (porta 3001)
cd finances-web
npm install && npm run dev
```

> [!TIP]
> Para instruções detalhadas, consulte o [Guia de Início Rápido](./Getting-Started.md)

---

## Contribuindo

Contribuições são bem-vindas! Consulte nosso [Guia de Contribuição](./Contributing.md) para:

- Code of Conduct
- Como reportar bugs
- Como propor novas features
- Fluxo de Pull Requests

---

## Licença

Este projeto está licenciado sob a licença **MIT** - veja o arquivo [LICENSE](../../LICENSE) para detalhes.

---

<div align="center">

Feito por [Gabriel Geronazzo](https://github.com/ImGabreuw)

</div>
