package com.kotlin.rent_flow.dtos.request

import java.math.BigDecimal
import java.util.UUID

data class CreateTenantRequest(
    val unitId: UUID,
    val name: String,
    val phoneNumber: String,
    val doorDescription: String,
    val rent: BigDecimal
)

data class UpdateTenantRequest(
    val name: String?,
    val phoneNumber: String?,
    val rent: BigDecimal?,
    val isActive: Boolean?
)
