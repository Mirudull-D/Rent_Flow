# Dashboard API

Detailed documentation for system-wide metrics.

---

### 1. Get Summary
`GET /dashboard/summary`

**Description**: Returns a snapshot of the entire property portfolio's health.

**Response Body**:
```json
{
  "totalBuildings": 3,
  "totalUnits": 45,
  "occupiedUnits": 40,
  "vacantUnits": 5,
  "activeTenants": 40,
  "totalCharges": 850,
  "pendingCharges": 12,
  "paidCharges": 830,
  "overdueCharges": 8,
  "totalPendingAmount": 150000.00,
  "totalCollectedAmount": 12500000.00,
  "totalOverdueAmount": 95000.00
}
```
