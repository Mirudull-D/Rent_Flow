package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.request.CreateBuildingRequest
import com.kotlin.rent_flow.dtos.response.BuildingDetailResponse
import com.kotlin.rent_flow.dtos.response.BuildingResponse
import java.util.UUID

interface BuildingServices {
    fun create(request: CreateBuildingRequest): BuildingResponse
    fun getall():List<BuildingResponse>
    fun getById(id: UUID): BuildingDetailResponse
}