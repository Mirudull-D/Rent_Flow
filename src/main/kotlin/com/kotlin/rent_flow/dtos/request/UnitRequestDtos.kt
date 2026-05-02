package com.kotlin.rent_flow.dtos.request

import java.util.UUID

data class CreateUnitRequest(
    val buildingId: UUID,
    val unitNumber: String,
    val ebNumber: String?
)