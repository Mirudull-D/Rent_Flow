package com.kotlin.rent_flow.dtos.response

import com.kotlin.rent_flow.enums.ChargeType
import com.kotlin.rent_flow.enums.FrequencyType
import com.kotlin.rent_flow.enums.ScopeType
import java.math.BigDecimal
import java.util.UUID

data class ChargeTemplateResponse(
    val id: UUID,
    val scope: ScopeType,
    val chargeType: ChargeType,
    val label: String,
    val defaultAmount: BigDecimal?,
    val frequency: FrequencyType,
    val dueDay: Int?,
    val isActive: Boolean
)