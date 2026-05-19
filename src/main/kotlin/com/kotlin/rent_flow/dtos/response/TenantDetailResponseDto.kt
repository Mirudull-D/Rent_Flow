package com.kotlin.rent_flow.dtos.response

import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.enums.ChargeType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class TenantDetailResponse(

    val id: UUID,

    val name: String,

    val phoneNumber: String,

    val doorDescription: String,

    val rent: BigDecimal,

    val isActive: Boolean,

    val building: TenantBuildingResponse,

    val unit: TenantUnitResponse,

    val financialSummary: TenantFinancialSummaryResponse,

    val charges: List<TenantChargeResponse>
)

data class TenantBuildingResponse(

    val id: UUID,

    val name: String,

    val address: String
)

data class TenantUnitResponse(

    val id: UUID,

    val unitNumber: String,

    val ebNumber: String?
)

data class TenantFinancialSummaryResponse(

    val pendingAmount: BigDecimal,

    val overdueAmount: BigDecimal,

    val paidThisMonth: BigDecimal
)

data class TenantChargeResponse(

    val id: UUID,

    val chargeType: ChargeType,

    val label: String,

    val amount: BigDecimal?,

    val status: ChargeStatus,

    val dueDate: LocalDate?,

    val paidDate: LocalDate?,

    val isOverdue: Boolean,

    val periodLabel: String?
)