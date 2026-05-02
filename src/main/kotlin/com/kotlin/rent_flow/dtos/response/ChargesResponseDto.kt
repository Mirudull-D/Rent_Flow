package com.kotlin.rent_flow.dtos.response

import com.kotlin.rent_flow.enums.ChargeStatus
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class ChargeResponse(
    val id: UUID,
    val label: String,
    val amount: BigDecimal?,
    val status: ChargeStatus,

    val tenantId: UUID?,
    val buildingId: UUID?,

    val periodMonth: Int?,
    val periodYear: Int?,

    val dueDate: LocalDate?,
    val paidDate: LocalDate?
)

data class ChargeSummaryResponse(
    val total: BigDecimal,
    val paid: BigDecimal,
    val pending: BigDecimal,
    val overdue: BigDecimal
)