# API Reference

The Rent_Flow API is structured into the following resource groups. Each link provides detailed documentation including request/response examples and functional descriptions.

###  API Resource Groups

- **[Buildings API](api/buildings.md)**: Manage properties, addresses, and building-level tax settings.
- **[Property Units API](api/units.md)**: Manage individual apartments, offices, and electricity meters.
- **[Tenants API](api/tenants.md)**: Manage tenant profiles, contact info, and active status.
- **[Charge Templates API](api/charge-templates.md)**: Configure rules for automated recurring billing.
- **[Charges API](api/charges.md)**: Handle individual billing records, payments, and manual charges.
- **[Dashboard API](api/dashboard.md)**: Access system-wide financial and occupancy metrics.

---

###  Global Conventions

#### Date/Time Format
All timestamps are returned in ISO-8601 format: `YYYY-MM-DDTHH:mm:ssZ`.

#### Status Codes
- `200 OK`: Request successful.
- `201 Created`: Resource created successfully.
- `204 No Content`: Resource deleted successfully.
- `400 Bad Request`: Validation error or malformed JSON.
- `404 Not Found`: Resource ID does not exist.
- `500 Internal Server Error`: Unexpected server error.
