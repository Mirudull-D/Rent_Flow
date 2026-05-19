package com.kotlin.rent_flow.dtos.response

import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.enums.ChargeType
import com.kotlin.rent_flow.enums.FrequencyType
import com.kotlin.rent_flow.enums.ScopeType
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

data class ChargeTemplateResponse(

    val id: UUID,

    val scope: ScopeType,

    val chargeType: ChargeType,

    val label: String,

    val defaultAmount: BigDecimal?,

    val amountIsFixed: Boolean,

    val frequency: FrequencyType,

    val dueDay: Int?,

    val autoGenerate: Boolean,

    val isActive: Boolean
)

data class ChargeTemplateDetailResponse(

    val id: UUID,

    val scope: ScopeType,

    val building: ChargeTemplateBuildingResponse?,

    val tenant: ChargeTemplateTenantResponse?,

    val chargeType: ChargeType,

    val label: String,

    val defaultAmount: BigDecimal?,

    val amountIsFixed: Boolean,

    val frequency: FrequencyType,

    val dueDay: Int?,

    val autoGenerate: Boolean,

    val startDate: Instant,

    val endDate: Instant?,

    val isActive: Boolean,

    val charges: List<ChargeTemplateChargeResponse>
)
data class ChargeTemplateBuildingResponse(

    val id: UUID,

    val name: String
)
data class ChargeTemplateTenantResponse(

    val id: UUID,

    val name: String
)
data class ChargeTemplateChargeResponse(

    val id: UUID,

    val chargeType: ChargeType,

    val label: String,

    val amount: BigDecimal?,

    val status: ChargeStatus,

    val dueDate: LocalDate?,

    val paidDate: LocalDate?,

    val periodLabel: String?,

    val isOverdue: Boolean
)