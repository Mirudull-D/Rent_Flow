package com.kotlin.rent_flow.dtos.response

import java.math.BigDecimal
import java.util.UUID

data class BuildingResponse(
    val id: UUID,
    val name: String,
    val address: String,
    val waterTaxAmount: BigDecimal,
    val propertyTaxAmount: BigDecimal
)

data class BuildingDetailResponse(
    val id: UUID,
    val name: String,
    val address: String,
    val waterTaxAmount: BigDecimal,
    val propertyTaxAmount: BigDecimal,

    val totalUnits: Int,
    val occupiedUnits: Int,
    val vacantUnits: Int,

    val units: List<UnitWithTenantResponse>,

    val activeTemplates: List<ChargeTemplateResponse>,

)
