package com.kotlin.rent_flow.dtos.request

import java.math.BigDecimal

data class CreateBuildingRequest(
    val name: String,
    val address: String,
    val waterTaxAmount: BigDecimal,
    val propertyTaxAmount: BigDecimal
)

data class UpdateBuildingRequest(
    val name: String?,
    val address: String?,
    val waterTaxAmount: BigDecimal?,
    val propertyTaxAmount: BigDecimal?
)
