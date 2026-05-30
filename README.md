# E-Banking Backend

This module contains the Spring Boot backend for the E-Banking application.

## 🚀 Features

- **RESTful API**: Endpoints for Customers, Bank Accounts, and Operations.
- **Security**: Secured with Spring Security and JWT.
- **Agentic AI**: Integrates Spring AI `@Tool` annotations allowing an AI agent (e.g., Telegram Bot) to extract real-time account data using natural language.
- **Dashboard Analytics**: Exposes statistical data (account distributions, monthly debit/credit aggregations) for the frontend dashboard.
- **Database Architecture**: Uses Spring Data JPA (compatible with H2, MySQL, etc.).

## 🛠️ Technologies

- Java 17
- Spring Boot
- Spring Security (JWT Tokens)
- Spring Data JPA
- Spring AI (for the Agentic system)
- Maven

## 🚦 Getting Started

1. Navigate to the backend directory:

   ```bash
   cd ebanking-backend
   ```

2. Run the Spring Boot application using the Maven wrapper:

   ```bash
   ./mvnw spring-boot:run
   ```

   _(On Windows, you can use `mvnw.cmd spring-boot:run`)_

3. The API will be accessible by default at `http://localhost:8085`.
