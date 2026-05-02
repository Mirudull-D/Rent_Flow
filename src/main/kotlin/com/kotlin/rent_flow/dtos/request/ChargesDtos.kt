package com.kotlin.rent_flow.dtos.request

import com.kotlin.rent_flow.enums.ChargeType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class CreateChargeRequest(
    val templateId: UUID?,
    val buildingId: UUID?,
    val tenantId: UUID?,

    val chargeType: ChargeType,
    val label: String,
    val amount: BigDecimal,

    val periodMonth: Int,
    val periodYear: Int,

    val dueDate: LocalDate
)
data class PayChargeRequest(
    val paidDate: LocalDate,
    val notes: String?
)