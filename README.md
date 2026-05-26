# Rent_Flow

Rent_Flow is a professional property management and rent tracking system built with Kotlin and Spring Boot. It provides a robust solution for managing buildings, units, tenants, and automated billing workflows.

---

##  Documentation Suite

For detailed information, please refer to the specific guides in the `docs/` folder:

- **[Architecture & Design](docs/architecture.md)**: Deep dive into the layered architecture, design patterns, and system components.
- **[API Reference](docs/api-reference.md)**: Complete list of REST endpoints, request/response examples, and DTO structures.
- **[Database Schema](docs/database-schema.md)**: Detailed ER diagrams and table definitions for the PostgreSQL backend.
- **[Business Logic Guide](docs/business-logic.md)**: Detailed explanation of automated charge generation, occupancy rules, and financial status lifecycles.
- **[Setup & Installation](docs/setup-guide.md)**: Step-by-step instructions for setting up the local development environment.

---

##  Core Features

- **Automated Billing**: Schedule-based charge generation (Rent, Taxes, Utilities).
- **Property Hierarchy**: Structured management from Buildings down to individual Units.
- **Financial Dashboard**: Real-time insights into revenue, occupancy, and overdue payments.
- **Receipt Management**: Integration with AWS S3 for secure payment document storage.
- **Kotlin-First**: Leveraging Kotlin's expressive syntax and Spring Boot's powerful ecosystem.

##  Quick Start

1. **Clone & Setup**:
   ```bash
   git clone https://github.com/your-username/Rent_Flow.git
   cd Rent_Flow
   ```
2. **Configure Database**: Update `application.properties` with your PostgreSQL credentials.
3. **Run**:
   ```bash
   ./gradlew bootRun
   ```
