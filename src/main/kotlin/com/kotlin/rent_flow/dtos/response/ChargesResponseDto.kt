package com.kotlin.rent_flow.dtos.response

import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.enums.ChargeType
import com.kotlin.rent_flow.enums.FrequencyType
import com.kotlin.rent_flow.enums.ScopeType
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class ChargeResponse(

    val id: UUID,
    val chargeType: ChargeType,
    val label: String,
    val amount: BigDecimal?,
    val status: ChargeStatus,
    val dueDate: LocalDate?,
    val tenantName: String?,
    val buildingName: String?
)

data class ChargeSummaryResponse(
    val total: BigDecimal,
    val paid: BigDecimal,
    val pending: BigDecimal,
    val overdue: BigDecimal
)
data class ChargeDetailResponse(

    val id: UUID,

    val scope: ScopeType,

    val chargeType: ChargeType,

    val label: String,

    val amount: BigDecimal?,

    val status: ChargeStatus,

    val periodLabel: String?,

    val periodMonth: Int?,

    val periodYear: Int?,

    val dueDate: LocalDate?,

    val paidDate: LocalDate?,

    val isOverdue: Boolean,

    val notes: String?,

    val receiptS3Key: String?,

    val building: ChargeBuildingResponse?,

    val tenant: ChargeTenantResponse?,

    val template: ChargeTemplateInfoResponse?
)
data class ChargeBuildingResponse(

    val id: UUID,

    val name: String,

    val address: String
)
data class ChargeTenantResponse(

    val id: UUID,

    val name: String,

    val phoneNumber: String,

    val unitNumber: String?
)
data class ChargeTemplateInfoResponse(

    val id: UUID,

    val label: String,

    val frequency: FrequencyType
)