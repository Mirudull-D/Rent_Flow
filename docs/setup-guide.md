# Setup & Installation Guide

Follow these steps to set up the Rent_Flow development environment on your local machine.

##  Prerequisites

- **JDK 17**: [Adoptium Temurin](https://adoptium.net/) is recommended.
- **PostgreSQL 14+**: Ensure you have a running instance and a database named `appdb`.
- **Gradle**: (Optional) The project includes a wrapper, so `gradle` isn't strictly required.
- **IntelliJ IDEA**: Recommended for Kotlin development.

## ️ Configuration

1. **Environment Variables**:
   You can set these in your shell or create a `.env` file (if supported by your runner):
   - `DB_URL`: `jdbc:postgresql://localhost:5432/appdb`
   - `DB_USER`: Your postgres username.
   - `DB_PASSWORD`: Your postgres password.

2. **Application Properties**:
   Review `src/main/resources/application.properties`. Ensure the datasource settings match your local DB.

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

##  Running the Application

### Using Gradle Wrapper
```bash
./gradlew bootRun
```

### Using IntelliJ
- Import the project as a Gradle project.
- Locate `RentFlowApplication.kt`.
- Click the "Run" icon next to the `main` function.

##  Database Setup

1. Create the database:
   ```sql
   CREATE DATABASE appdb;
   ```
2. The tables will be automatically created on the first run thanks to `hibernate.ddl-auto=update`.

##  Testing

Run the test suite to ensure everything is configured correctly:

```bash
./gradlew test
```

##  AWS S3 (Optional)
To enable receipt uploads, you must provide AWS credentials with S3 permissions. If not provided, receipt-related features will be disabled or return errors.
