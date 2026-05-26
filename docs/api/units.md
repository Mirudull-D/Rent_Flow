# Property Units API

Detailed documentation for managing individual units within buildings.

---

### 1. Create Property Unit
`POST /units`

**Description**: Registers a new unit (e.g., apartment, office) and assigns it to a building.

**Request Body**:
```json
{
  "buildingId": "550e8400-e29b-41d4-a716-446655440000",
  "unitNumber": "A-101",
  "ebNumber": "EB-123456"
}
```

**Response Body**:
```json
{
  "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "unitNumber": "A-101",
  "ebNumber": "EB-123456",
  "isOccupied": false,
  "isActive": true
}
```

---

### 2. List Units by Building
`GET /buildings/{buildingId}/units`

**Description**: Returns all units for a specific building.

**Response Body**:
```json
[
  {
    "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
    "unitNumber": "A-101",
    "ebNumber": "EB-123456",
    "isOccupied": true,
    "isActive": true
  }
]
```

---

### 3. Get Unit by ID
`GET /units/{id}`

**Description**: Retrieves detailed unit info, including its tenant history/current tenant.

**Response Body**:
```json
{
  "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "unitNumber": "A-101",
  "isOccupied": true,
  "isActive": true,
  "tenant": [
    {
      "id": "...",
      "name": "Jane Doe",
      "phoneNumber": "9876543210",
      "rent": 15000.00,
      "isActive": true
    }
  ]
}
```

---

### 4. Update Unit
`PUT /units/{id}`

**Description**: Updates unit-specific details like the unit number or electricity meter number.

**Request Body**:
```json
{
  "unitNumber": "A-101-Updated",
  "ebNumber": "EB-654321"
}
```

**Response Body**:
```json
{
  "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "unitNumber": "A-101-Updated",
  "isOccupied": true,
  "isActive": true,
  "tenant": { ... }
}
```

---

### 5. Delete Unit
`DELETE /units/{id}`

**Description**: Deletes a property unit.

**Response**: `204 No Content`
