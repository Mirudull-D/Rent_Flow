package com.kotlin.rent_flow.dtos.response

import java.math.BigDecimal
import java.util.UUID

data class TenantResponse(
    val id: UUID,
    val name: String,
    val phoneNumber: String,
    val doorDescription: String,
    val rent: BigDecimal,
    val isActive: Boolean
)
