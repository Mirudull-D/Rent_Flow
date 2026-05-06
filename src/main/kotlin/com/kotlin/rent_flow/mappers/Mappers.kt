package com.kotlin.rent_flow.mappers

import com.kotlin.rent_flow.dtos.response.BuildingResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse
import com.kotlin.rent_flow.dtos.response.PropertyUnitResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponses
import com.kotlin.rent_flow.entiites.Building
import com.kotlin.rent_flow.entiites.ChargeTemplate
import com.kotlin.rent_flow.entiites.PropertyUnit

object Mappers {
     fun mapToResponse(building: Building): BuildingResponse {
        return BuildingResponse(
            id = building.id!!,
            name = building.name,
            address = building.address,
            waterTaxAmount = building.waterTaxAmount,
            propertyTaxAmount = building.propertyTaxAmount
        )
    }

    fun mapToChargeTemplateResponse(template: ChargeTemplate): ChargeTemplateResponse {

        return ChargeTemplateResponse(
            id = template.id!!,
            scope = template.scope,
            chargeType = template.chargeType,
            label = template.label,
            defaultAmount = template.defaultAmount,
            frequency = template.frequency,
            dueDay = template.dueDay,
            isActive = template.isActive,
        )

}
    fun toUnitResponse(unit: PropertyUnit): PropertyUnitResponse {
        return PropertyUnitResponse(
            id = unit.id!!,
            unitNumber = unit.unitNumber,
            ebNumber = unit.ebNumber,
            isOccupied = unit.isOccupied,
            isActive = unit.isActive
        )
    }

    fun toUnitTenantResponses(unit: PropertyUnit): UnitWithTenantResponses
    {
        val tenants = unit.tenants
        return UnitWithTenantResponses(
            id = unit.id!!,
            unitNumber = unit.unitNumber,
            isOccupied = unit.isOccupied,
            isActive = unit.isActive,
            tenant = tenants.map {
                TenantResponse(
                    id = it.id!!,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    doorDescription = it.doorDescription,
                    rent = it.rent,
                    isActive = it.isActive,
                ) }
        )
    }fun toUnitTenantResponse(unit: PropertyUnit): UnitWithTenantResponse
    {
        val tenants = unit.tenants.firstOrNull()
        if (tenants != null)
        return UnitWithTenantResponse(
            id = unit.id!!,
            unitNumber = unit.unitNumber,
            isOccupied = unit.isOccupied,
            isActive = unit.isActive,
            tenant =
                TenantResponse(
                    id = tenants.id!!,
                    name = tenants.name,
                    phoneNumber = tenants.phoneNumber,
                    doorDescription = tenants.doorDescription,
                    rent = tenants.rent,
                    isActive = tenants.isActive,
                )
        )
        else
            return UnitWithTenantResponse(
                id = unit.id!!,
                unitNumber = unit.unitNumber,
                isOccupied = unit.isOccupied,
                isActive = unit.isActive,
                tenant = null
            )
    }
}
