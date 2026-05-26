# Database Schema

Rent_Flow uses a PostgreSQL database to store persistent records. The schema is managed by Hibernate with automatic updates.

##  Entity Relationship Diagram

```mermaid
erDiagram
    BUILDINGS ||--o{ PROPERTY_UNITS : contains
    PROPERTY_UNITS ||--o{ TENANTS : houses
    TENANTS ||--o{ CHARGES : pays
    BUILDINGS ||--o{ CHARGES : incurs
    CHARGE_TEMPLATES ||--o{ CHARGES : generates
    TENANTS ||--o{ CHARGE_TEMPLATES : assigned_to
    BUILDINGS ||--o{ CHARGE_TEMPLATES : assigned_to

    BUILDINGS {
        uuid id PK
        string name
        string address
        decimal water_tax_amount
        decimal property_tax_amount
        timestamp created_at
        timestamp updated_at
    }

    PROPERTY_UNITS {
        uuid id PK
        uuid building_id FK
        string unit_number
        string eb_number
        boolean is_active
    }

    TENANTS {
        uuid id PK
        uuid unit_id FK
        string name
        string phone_number
        decimal rent
        boolean is_active
    }

    CHARGE_TEMPLATES {
        uuid id PK
        string scope
        uuid building_id FK
        uuid tenant_id FK
        string charge_type
        string label
        decimal default_amount
        string frequency
        int due_day
        boolean auto_generate
        boolean is_active
    }

    CHARGES {
        uuid id PK
        uuid template_id FK
        uuid building_id FK
        uuid tenant_id FK
        string status
        decimal amount
        date due_date
        date paid_date
        string receipt_s3_key
    }
```

##  Table Definitions

### `buildings`
Primary entity representing a physical property.
- `id`: UUID, Primary Key.
- `name`: String, name of the building.
- `water_tax_amount`: Yearly water tax estimate.

### `property_units`
Specific units (apartments/offices) within a building.
- `building_id`: Foreign Key to `buildings`.
- `unit_number`: String, unique within building.

### `tenants`
The occupants of a property unit.
- `unit_id`: Foreign Key to `property_units`.
- `rent`: The base monthly rent for this tenant.

### `charge_templates`
Configuration for automated billing.
- `frequency`: Enum (MONTHLY, YEARLY, etc.).
- `scope`: Enum (TENANT or BUILDING).

### `charges`
Individual billing records.
- `status`: Enum (PENDING, PAID, DRAFT).
- `period_month`/`period_year`: Used to track billing periods.
