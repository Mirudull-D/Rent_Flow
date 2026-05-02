package com.kotlin.rent_flow.dtos.request

import com.kotlin.rent_flow.enums.ChargeType
import com.kotlin.rent_flow.enums.FrequencyType
import com.kotlin.rent_flow.enums.ScopeType
import java.math.BigDecimal
import java.util.UUID
import kotlin.time.Instant

data class CreateChargeTemplateRequest(
    val scope: ScopeType,
    val buildingId: UUID?,
    val tenantId: UUID?,

    val chargeType: ChargeType,
    val label: String,

    val defaultAmount: BigDecimal?,
    val amountIsFixed: Boolean,

    val frequency: FrequencyType,
    val dueDay: Int?,

    val startDate: Instant,
    val endDate: Instant?
)