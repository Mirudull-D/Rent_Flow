# Rent_Flow

Rent_Flow is a professional property management and rent tracking system built with Kotlin and Spring Boot. It provides a robust solution for managing buildings, units, tenants, and automated billing workflows.

Successfully handled 100 concurrent users with ~451 requests/sec, achieving a P90 latency of 125ms and P99 latency under 500ms on a self-hosted Dockerized Spring Boot deployment.

---

##  Documentation Suite

For detailed information, please refer to the specific guides in the `docs/` folder:

- **[Architecture & Design](docs/architecture.md)**: Deep dive into the layered architecture, design patterns, and system components.
- **[API Reference](docs/api-reference.md)**: Complete list of REST endpoints, request/response examples, and DTO structures.
- **[Database Schema](docs/database-schema.md)**: Detailed ER diagrams and table definitions for the PostgreSQL backend.
- **[Business Logic Guide](docs/business-logic.md)**: Detailed explanation of automated charge generation, occupancy rules, and financial status lifecycles.
- **[Setup & Installation](docs/setup-guide.md)**: Step-by-step instructions for setting up the local development environment.


---

# Tech Stack

| Layer            | Technology                  |
| ---------------- | --------------------------- |
| Backend          | Kotlin + Spring Boot        |
| Database         | PostgreSQL                  |
| ORM              | Spring Data JPA / Hibernate |
| Build Tool       | Gradle                      |
| Containerization | Docker                      |
| Load Testing     | k6                          |
| Deployment       | Ubuntu Server               |

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
   
# Docker Deployment

## Build JAR

```bash
./gradlew build -x test
```

---

## Build Docker Image

```bash
docker build -t rentflow .
```

---

## Run Container

```bash
docker run -d \
-p 8080:8080 \
--name rentflow-app \
rentflow
```

---

## View Logs

```bash
docker logs -f rentflow-app
```

---

# Performance Benchmark

RentFlow was benchmarked using k6 against a Dockerized deployment running on Ubuntu Server.

## Test Environment

* Ubuntu Server 24.04
* Docker Engine
* PostgreSQL 16
* Lenovo ThinkPad T450s
* Intel i5
* 8GB RAM

---

## Load Test Configuration

* Tool: k6
* Concurrent Users: 100
* Duration: 30 seconds
* Throughput: ~451 requests/sec

---

## Benchmark Results

| Metric                | Result |
| --------------------- | ------ |
| Average Response Time | 49ms   |
| Median Response Time  | 12ms   |
| P90                   | 125ms  |
| P95                   | 289ms  |
| P99                   | 493ms  |
| Max Response Time     | 810ms  |
| Failed Requests       | 0%     |

---

## Endpoints Benchmarked

* GET /buildings
* GET /buildings/{id}
* GET /buildings/{id}/units
* GET /tenants/{id}
* GET /charge/tenant/{tenantId}
* GET /charge-templates

---

# Deployment Architecture

```text
Client
   ↓
Ubuntu Server
   ↓
Docker Container
   ↓
Spring Boot Application
   ↓
PostgreSQL Database
```

---


