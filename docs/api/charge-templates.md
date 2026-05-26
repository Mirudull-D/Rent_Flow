# Charge Templates API

Documentation for configuring automated billing workflows.

---

### 1. Create Template
`POST /charge-templates`

**Description**: Defines a blueprint for recurring charges.

**Request Body**:
```json
{
  "scope": "TENANT",
  "tenantId": "7890-uuid",
  "chargeType": "RENT",
  "label": "Monthly Rent",
  "defaultAmount": 15000.00,
  "amountIsFixed": true,
  "frequency": "MONTHLY",
  "dueDay": 5,
  "startDate": "2024-01-01T00:00:00Z",
  "autoGenerate": true,
  "isActive": true
}
```

**Response Body**:
```json
{
  "id": "temp-uuid",
  "scope": "TENANT",
  "label": "Monthly Rent",
  "frequency": "MONTHLY",
  "isActive": true
}
```

---

### 2. Get Template Detail
`GET /charge-templates/{id}`

**Description**: Returns template configuration and a history of generated charges.

**Response Body**:
```json
{
  "id": "temp-uuid",
  "label": "Monthly Rent",
  "startDate": "2024-01-01T00:00:00Z",
  "frequency": "MONTHLY",
  "charges": [
    {
      "id": "charge-1",
      "amount": 15000.00,
      "status": "PAID",
      "periodLabel": "JANUARY 2024"
    }
  ]
}
```

---

### 3. Update Template
`PUT /charge-templates/{id}`

**Description**: Modifies the billing rules (e.g., changing the default amount or frequency).

**Request Body**:
```json
{
  "label": "Updated Rent Label",
  "defaultAmount": 16000.00,
  "dueDay": 10
}
```

**Response Body**:
```json
{
  "id": "temp-uuid",
  "label": "Updated Rent Label",
  "defaultAmount": 16000.00
}
```

---

### 4. Activate/Deactivate
`PATCH /charge-templates/{id}/activate`  
`PATCH /charge-templates/{id}/deactivate`

**Description**: Toggles the automated generation for this template.

**Response**: `200 OK`
