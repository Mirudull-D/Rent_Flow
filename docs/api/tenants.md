# Tenants API

Detailed documentation for managing tenants and their occupancy details.

---

### 1. Create Tenant
`POST /units/{unitId}/tenants`

**Description**: Assigns a new tenant to a property unit and sets their base rent.

**Request Body**:
```json
{
  "name": "John Doe",
  "phoneNumber": "9988776655",
  "doorDescription": "Second floor, East facing",
  "rent": 12500.00
}
```

**Response Body**:
```json
{
  "id": "7890-uuid",
  "name": "John Doe",
  "phoneNumber": "9988776655",
  "doorDescription": "Second floor, East facing",
  "rent": 12500.00,
  "isActive": true
}
```

---

### 2. List Tenants by Unit
`GET /units/{unitId}/tenants`

**Description**: Retrieves a list of all tenants associated with a unit.

**Response Body**:
```json
[
  {
    "id": "7890-uuid",
    "name": "John Doe",
    "phoneNumber": "9988776655",
    "rent": 12500.00,
    "isActive": true
  }
]
```

---

### 3. Get Tenant by ID
`GET /tenants/{id}`

**Description**: Returns a full tenant profile, including building/unit details and financial summary (pending/paid amounts).

**Response Body**:
```json
{
  "id": "7890-uuid",
  "name": "John Doe",
  "phoneNumber": "9988776655",
  "financialSummary": {
    "pendingAmount": 500.00,
    "overdueAmount": 0.00,
    "paidThisMonth": 12500.00
  },
  "charges": [
    {
      "id": "...",
      "label": "May Rent",
      "amount": 12500.00,
      "status": "PAID"
    }
  ],
  "building": { "name": "Emerald Heights" },
  "unit": { "unitNumber": "A-101" }
}
```

---

### 4. Update Tenant
`PUT /tenants/{id}`

**Description**: Updates tenant contact info or rent agreement.

**Request Body**:
```json
{
  "name": "John Doe Jr.",
  "phoneNumber": "9000000000",
  "rent": 13000.00,
  "doorDescription": "Updated description"
}
```

**Response Body**:
```json
{
  "id": "7890-uuid",
  "name": "John Doe Jr.",
  "phoneNumber": "9000000000",
  "rent": 13000.00,
  "isActive": true
}
```

---

### 5. Delete Tenant
`DELETE /tenants/{id}`

**Description**: Removes a tenant record.

**Response**: `204 No Content`
