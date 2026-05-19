package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.request.CreateUnitRequest
import com.kotlin.rent_flow.dtos.request.UpdateUnitRequest
import com.kotlin.rent_flow.dtos.response.PropertyUnitResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponses
import com.kotlin.rent_flow.entiites.PropertyUnit
import com.kotlin.rent_flow.repositories.BuildingRepository
import com.kotlin.rent_flow.repositories.PropertyUnitRepository
import com.kotlin.rent_flow.services.PropertyUnitService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import com.kotlin.rent_flow.mappers.Mappers.toUnitResponse
import com.kotlin.rent_flow.mappers.Mappers.toUnitTenantResponses
import com.kotlin.rent_flow.mappers.Mappers.toUnitTenantResponse
import com.kotlin.rent_flow.repositories.TenantRepository
import java.util.UUID

@Service
class PropertyUnitServiceImpl(
    private val buildingRepository: BuildingRepository,
    private val propertyUnitRepository: PropertyUnitRepository,
    private val tenantRepository: TenantRepository
): PropertyUnitService {

    @Transactional
    override fun create(
        unitRequest: CreateUnitRequest
    ): PropertyUnitResponse {
        val building = buildingRepository.findById(unitRequest.buildingId)
            .orElseThrow { throw IllegalArgumentException("Invalid BuildingID") }

        val exist = propertyUnitRepository
            .existsByBuilding_IdAndUnitNumber(building.id!!,unitRequest.unitNumber)

        if(exist){
            throw IllegalArgumentException("Unit Number already in use")
        }
        val unit = PropertyUnit(
            building = building,
            unitNumber = unitRequest.unitNumber,
            ebNumber = unitRequest.ebNumber,
        )
        val saved= propertyUnitRepository.save(unit)

        return toUnitResponse(unit)
    }

    override fun getByBuilding(buildingId: UUID): List<PropertyUnitResponse> {

        val building = buildingRepository.findById(buildingId)
            .orElseThrow { throw IllegalArgumentException("Building Dosnt exist") }
        val units = propertyUnitRepository.findAllByBuilding_Id(buildingId)

        return units.map { it -> toUnitResponse(it) }

    }

    override fun getById(unitId: UUID): UnitWithTenantResponses {

        val unit = propertyUnitRepository.findByIdWithTenants(unitId)
            ?: throw IllegalArgumentException("Unit dose not exist")

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
    }

    @Transactional
    override fun update( unitId:UUID , unitRequest: UpdateUnitRequest): UnitWithTenantResponse {

        val unit = propertyUnitRepository.findByIdWithTenants(unitId)
            ?: throw IllegalArgumentException("Unit dose not exist")

        unitRequest.unitNumber?.let {
            if (!unitRequest.unitNumber.isBlank()) {
                unit.unitNumber=unitRequest.unitNumber
            }

        }
        unitRequest.ebNumber?.let {
            if (unitRequest.ebNumber.isBlank()) {
            unit.ebNumber=unitRequest.ebNumber
            }
        }
        val saved = propertyUnitRepository.save(unit)

        return toUnitTenantResponse(unit)

    }

    @Transactional
    override fun delete(id: UUID) {
        if (!tenantRepository.existsByPropertyUnit_IdAndIsActiveTrue(id)){
            throw IllegalArgumentException("Property has Active Members")
        }
        val unit = propertyUnitRepository.findById(id)
            .orElseThrow{throw IllegalArgumentException("Unit dose not exist")}
        unit.isActive = false
        propertyUnitRepository.save(unit)
        return
    }


}