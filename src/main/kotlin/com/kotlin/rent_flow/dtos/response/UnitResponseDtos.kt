package com.kotlin.rent_flow.dtos.response

import java.util.UUID

data class PropertyUnitResponse(
    val id: UUID,
    val unitNumber: String,
    val ebNumber: String?,
    val isOccupied: Boolean,
    val isActive: Boolean
)

data class UnitWithTenantResponse(
    val id: UUID,
    val unitNumber: String,
    val isOccupied: Boolean,
    val isActive: Boolean,
    val tenant: TenantResponse?
)
data class UnitWithTenantResponses(
    val id: UUID,
    val unitNumber: String,
    val isOccupied: Boolean,
    val isActive: Boolean,
    val tenant: List<TenantResponse>?
)