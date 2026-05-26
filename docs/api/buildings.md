# Buildings API

Detailed documentation for managing physical buildings and properties.

---

### 1. List All Buildings
`GET /buildings`

**Description**: Retrieves a list of all buildings registered in the system.

**Response Body**:
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Emerald Heights",
    "address": "123 Green St, City",
    "waterTaxAmount": 500.00,
    "propertyTaxAmount": 1200.00
  }
]
```

---

### 2. Create Building
`POST /buildings`

**Description**: Creates a new building record with tax configuration.

**Request Body**:
```json
{
  "name": "Emerald Heights",
  "address": "123 Green St, City",
  "waterTaxAmount": 500.00,
  "propertyTaxAmount": 1200.00
}
```

**Response Body**:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Emerald Heights",
  "address": "123 Green St, City",
  "waterTaxAmount": 500.00,
  "propertyTaxAmount": 1200.00
}
```

---

### 3. Get Building by ID
`GET /buildings/{id}`

**Description**: Retrieves a comprehensive view of a building, including its units and active billing templates.

**Response Body**:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Emerald Heights",
  "address": "123 Green St, City",
  "waterTaxAmount": 500.00,
  "propertyTaxAmount": 1200.00,
  "totalUnits": 10,
  "occupiedUnits": 8,
  "vacantUnits": 2,
  "units": [
    {
      "id": "a1b2c3d4-...",
      "unitNumber": "101",
      "isOccupied": true,
      "isActive": true,
      "tenant": {
        "id": "...",
        "name": "John Doe",
        "phoneNumber": "9876543210"
      }
    }
  ],
  "activeTemplates": [
    {
      "id": "...",
      "scope": "BUILDING",
      "chargeType": "PROPERTY_TAX",
      "label": "Annual Tax",
      "frequency": "YEARLY"
    }
  ]
}
```

---

### 4. Update Building
`PUT /buildings/{id}`

**Description**: Updates the configuration of an existing building.

**Request Body**:
```json
{
  "name": "Emerald Heights Updated",
  "address": "124 Green St, City",
  "waterTaxAmount": 550.00,
  "propertyTaxAmount": 1300.00
}
```

**Response Body**:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Emerald Heights Updated",
  "address": "124 Green St, City",
  "waterTaxAmount": 550.00,
  "propertyTaxAmount": 1300.00
}
```

---

### 5. Delete Building
`DELETE /buildings/{id}`

**Description**: Permanently deletes a building and its associations.

**Response**: `204 No Content`
