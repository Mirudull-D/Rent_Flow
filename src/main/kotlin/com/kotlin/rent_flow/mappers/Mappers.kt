package com.kotlin.rent_flow.mappers

import com.kotlin.rent_flow.dtos.response.BuildingResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse
import com.kotlin.rent_flow.dtos.response.PropertyUnitResponse
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
            isOccupied = unit.isOccupied
        )
    }

}
