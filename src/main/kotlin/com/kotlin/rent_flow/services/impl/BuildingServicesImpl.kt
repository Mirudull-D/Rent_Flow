package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.request.CreateBuildingRequest
import com.kotlin.rent_flow.dtos.response.BuildingDetailResponse
import com.kotlin.rent_flow.dtos.response.BuildingResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponse
import com.kotlin.rent_flow.entiites.Building
import com.kotlin.rent_flow.entiites.ChargeTemplate
import com.kotlin.rent_flow.mappers.Mappers.mapToChargeTemplateResponse
import com.kotlin.rent_flow.repositories.BuildingRepository
import com.kotlin.rent_flow.repositories.ChargeTemplateRepository
import com.kotlin.rent_flow.services.BuildingServices
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID
import com.kotlin.rent_flow.mappers.Mappers.mapToResponse

@Service
class BuildingServicesImpl(
    private val buildingRepository: BuildingRepository,
    private val chargeTemplateRepository: ChargeTemplateRepository,
) : BuildingServices {
    override fun create(request: CreateBuildingRequest): BuildingResponse {
        if (request.waterTaxAmount < BigDecimal.ZERO ||
            request.propertyTaxAmount < BigDecimal.ZERO) {
            throw IllegalArgumentException("Tax amounts must be positive.")
        }

        val building = Building(
            name = request.name,
            address = request.address,
            waterTaxAmount = request.waterTaxAmount,
            propertyTaxAmount = request.propertyTaxAmount,
        )

        val saved = buildingRepository.save(building)

        return mapToResponse(saved)
    }

    override fun getall(): List<BuildingResponse> {
        val buildings = buildingRepository.findAll()

        return buildings.map { mapToResponse(it) }
    }

    @Transactional
    override fun getById(id: UUID): BuildingDetailResponse {

        val building = buildingRepository.findByIdWithUnitsAndTenants(id)
            ?: throw IllegalArgumentException("No building with id $id")

        val units = building.units
        val totalUnits = units.size

        val occupiedUnits = units.count{it.isOccupied}
        val vacantUnits = units.count{!it.isOccupied}

        val unitsWithTenants: List<UnitWithTenantResponse> = units.map { unit ->
            val activeTenant = unit.tenants.firstOrNull{ it.isActive }

            UnitWithTenantResponse(
                id = unit.id!!,
                unitNumber = unit.unitNumber,
                isOccupied = unit.isOccupied,
                tenant = activeTenant?.let {
                    TenantResponse(
                    id = it.id!!,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    doorDescription = it.doorDescription,
                    rent = it.rent,
                    isActive = it.isActive,
                )
                }
            )
        }


        val chargeTemplates : List<ChargeTemplate> = chargeTemplateRepository
            .findAllByBuilding_IdAndIsActiveTrue(building.id!!)
        
        val activeChargeTemplates : List<ChargeTemplateResponse> = chargeTemplates.map { 
            template ->mapToChargeTemplateResponse(template) }
        

        return BuildingDetailResponse(
            id = building.id !!,
            name = building.name,
            address = building.address,
            waterTaxAmount = building.waterTaxAmount,
            propertyTaxAmount = building.propertyTaxAmount,
            totalUnits = totalUnits,
            occupiedUnits = occupiedUnits,
            vacantUnits = vacantUnits,
            units = unitsWithTenants,
            activeTemplates = activeChargeTemplates
        )
    }




}