package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.repositories.PropertyUnitRepository
import com.kotlin.rent_flow.repositories.TenantRepository
import com.kotlin.rent_flow.services.TenantServices
import java.util.UUID
import com.kotlin.rent_flow.mappers.TenantMappers.toResponse
import org.springframework.stereotype.Service

@Service
class TenantServicesImpl(
    private val propertyUnitRepository: PropertyUnitRepository,
    private val tenantRepository: TenantRepository
): TenantServices {
    override fun getByUnit(unitId: UUID): List<TenantResponse> {

        val tenants = tenantRepository.findAllByPropertyUnit_Id(unitId)

        return tenants.map { toResponse(it) }
    }
}