# Charges API

Documentation for managing individual billing records.

---

### 1. List Charges
`GET /charge`

**Description**: Retrieves all charges. Filterable by `tenantId` or `buildingId` via dedicated endpoints.

**Response Body**:
```json
[
  {
    "id": "charge-uuid",
    "chargeType": "RENT",
    "label": "May Rent",
    "amount": 15000.00,
    "status": "PENDING",
    "dueDate": "2024-05-05"
  }
]
```

---

### 2. Create Manual Charge
`POST /charge/manual`

**Description**: Creates a one-off charge (e.g., for a repair or penalty).

**Request Body**:
```json
{
  "scope": "TENANT",
  "tenantId": "tenant-uuid",
  "chargeType": "MISC",
  "label": "Sink Repair",
  "amount": 750.00,
  "dueDate": "2024-05-25",
  "notes": "Fixed leaking pipe in kitchen"
}
```

**Response Body**:
```json
{
  "id": "manual-charge-uuid",
  "label": "Sink Repair",
  "amount": 750.00,
  "status": "PENDING"
}
```

---

### 3. Mark as Paid
`PATCH /charge/{id}/mark-paid`

**Description**: Sets the status to `PAID` and logs the current date as the `paidDate`.

**Response Body**:
```json
{
  "id": "charge-uuid",
  "status": "PAID",
  "paidDate": "2024-05-15"
}
```

---

### 4. Mark as Pending
`PATCH /charge/{id}/mark-pending`

**Description**: Reverts a charge to `PENDING` status.

**Response Body**:
```json
{
  "id": "charge-uuid",
  "status": "PENDING",
  "paidDate": null
}
```
