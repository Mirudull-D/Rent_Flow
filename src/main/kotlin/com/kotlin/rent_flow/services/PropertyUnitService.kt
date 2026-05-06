package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.request.CreateUnitRequest
import com.kotlin.rent_flow.dtos.request.UpdateUnitRequest
import com.kotlin.rent_flow.dtos.response.PropertyUnitResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponses
import java.util.UUID

interface PropertyUnitService {
    fun create ( unitRequest: CreateUnitRequest): PropertyUnitResponse
    fun getByBuilding( buildingId: UUID ) : List<PropertyUnitResponse>
    fun getById( unitId: UUID ): UnitWithTenantResponses
    fun update( unitId: UUID , unitRequest: UpdateUnitRequest ) : UnitWithTenantResponse
    fun delete(id :UUID )
}