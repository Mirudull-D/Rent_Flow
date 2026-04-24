package com.kotlin.rent_flow.enums

enum class ScopeType {
    TENANT, BUILDING
}
enum class ChargeType {
    RENT, EB_BILL, PARKING, WATER_TAX, PROPERTY_TAX, PHONE_BILL, MAINTENANCE, MISC
}
enum class FrequencyType {
    MONTHLY, BIMONTHLY, QUARTERLY, HALFYEARLY, YEARLY, ONETIME
}
enum class ChargeStatus {
    DRAFT, PENDING, PAID
}