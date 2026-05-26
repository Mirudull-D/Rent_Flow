# Business Logic Guide

This document explains the core business rules and automated processes within the Rent_Flow system.

##  Charge Generation Logic

The heart of Rent_Flow is its ability to automatically generate billing records. This is governed by **Charge Templates**.

### Charge Templates
A template acts as a blueprint for recurring or one-time charges.
- **Scope**: Can be `BUILDING` (e.g., Property Tax) or `TENANT` (e.g., Rent).
- **Frequency**: Defines how often the charge repeats:
    - `MONTHLY`: Every month.
    - `BIMONTHLY`: Every 2 months.
    - `QUARTERLY`: Every 3 months.
    - `HALFYEARLY`: Every 6 months.
    - `YEARLY`: Every 12 months.
    - `ONETIME`: Once on the start date.

### The Generation Algorithm
Every day at 01:00 AM, the `ChargeScheduler` runs. For each active template:
1. **Date Check**: It compares the `startDate` and the current date.
2. **Frequency Calculation**: It calculates the number of months between the start date and the current month.
3. **Modulo Check**: If `monthsBetween % frequencyInterval == 0`, a charge is eligible.
4. **Idempotency**: It checks the `charges` table to see if a record already exists for that `templateId`, `month`, and `year`.
5. **Creation**: If eligible and not a duplicate, a new `Charge` is created with status `PENDING`.

##  Property Hierarchy

The system follows a strict hierarchy:
1. **Building**: The top-level entity.
2. **Property Unit**: Belongs to a Building.
3. **Tenant**: Assigned to a Property Unit.

### Occupancy Rules
- A `PropertyUnit` is considered **Occupied** if it has at least one **Active** tenant.
- A unit can have multiple tenants over its lifetime, but typically only one active tenant at a time.

##  Dashboard Metrics

The Dashboard provides real-time financial and operational health:
- **Total Revenue**: Sum of all `PAID` charges.
- **Pending Amount**: Sum of all `PENDING` charges that are not yet overdue.
- **Overdue Amount**: Sum of all `PENDING` charges where the `dueDate` has passed.
- **Occupancy Rate**: Percentage of units with active tenants.

##  Status Lifecycle

### Charge Statuses
- `DRAFT`: Initial state (optional).
- `PENDING`: The charge has been issued but not yet paid.
- `PAID`: Payment has been confirmed and a receipt (optional) has been uploaded.

### Overdue Logic
A charge is automatically marked as `isOverdue` in the API response if:
- Status is `PENDING`.
- `dueDate` is not null.
- `dueDate` is before `LocalDate.now()`.
