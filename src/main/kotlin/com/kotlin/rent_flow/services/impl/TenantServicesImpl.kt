package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.request.CreateTenantRequest
import com.kotlin.rent_flow.dtos.request.UpdateTenantRequest
import com.kotlin.rent_flow.dtos.response.TenantBuildingResponse
import com.kotlin.rent_flow.dtos.response.TenantDetailResponse
import com.kotlin.rent_flow.dtos.response.TenantFinancialSummaryResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.dtos.response.TenantUnitResponse
import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.repositories.PropertyUnitRepository
import com.kotlin.rent_flow.repositories.TenantRepository
import com.kotlin.rent_flow.services.TenantServices
import com.kotlin.rent_flow.mappers.TenantMappers.toChargesTemplate
import com.kotlin.rent_flow.mappers.TenantMappers.toTenantEntity
import java.util.UUID
import com.kotlin.rent_flow.mappers.TenantMappers.toResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class TenantServicesImpl(
    private val propertyUnitRepository: PropertyUnitRepository,
    private val tenantRepository: TenantRepository
): TenantServices {
    override fun getByUnit(unitId: UUID): List<TenantResponse> {

        val tenants = tenantRepository.findAllByPropertyUnit_Id(unitId)

        return tenants.map { toResponse(it) }
    }

    @Transactional
    override fun getById(id: UUID): TenantDetailResponse {

        val tenant = tenantRepository.findById(id)
            .orElseThrow { throw IllegalArgumentException("Tenant not found") }

        val unit = tenant.propertyUnit

        val building = unit.building

        val charges = tenant.charges

        val pendingAmount = charges
            .filter { it.status == ChargeStatus.PENDING }
            .mapNotNull { it.amount }
            .fold(BigDecimal.ZERO, BigDecimal::add)

        val currentMonth = LocalDate.now().monthValue
        val currentYear = LocalDate.now().year

        val overdueAmount = charges
            .filter { it.isOverdue }
            .mapNotNull { it.amount }
            .fold(BigDecimal.ZERO, BigDecimal::add)

        val paidThisMonth = charges
            .filter {
                it.status == ChargeStatus.PAID &&
                        it.periodMonth == currentMonth &&
                        it.periodYear == currentYear
            }
            .mapNotNull { it.amount }
            .fold(BigDecimal.ZERO, BigDecimal::add)


        return TenantDetailResponse(
            id = tenant.id!!,
            name = tenant.name,
            phoneNumber = tenant.phoneNumber,
            doorDescription = tenant.doorDescription,
            rent = tenant.rent,
            isActive = tenant.isActive,
            building = TenantBuildingResponse(
                id = building.id!!,
                name = building.name,
                address = building.address,
            ),
            unit = TenantUnitResponse(
                id = unit.id!!,
                unitNumber = unit.unitNumber,
                ebNumber = unit.ebNumber,
            ),
            financialSummary = TenantFinancialSummaryResponse(
                pendingAmount ,
                overdueAmount ,
                paidThisMonth
            ),
            charges = toChargesTemplate(charges)
        )
    }

    @Transactional
    override fun create(
        unitId: UUID,
        tenant: CreateTenantRequest
    ): TenantResponse {
        val unit = propertyUnitRepository.findById(unitId)
            .orElseThrow {
                IllegalArgumentException("Unit not found")
            }

        val isOccupied = tenantRepository.existsByPropertyUnit_IdAndIsActiveTrue(unitId)

        if (isOccupied) {
            throw IllegalArgumentException("Unit Occupied Already")
        }

        val newtenant = toTenantEntity(tenant, unit)

        tenantRepository.save(newtenant)

        return toResponse(newtenant)
    }

    override fun update(
        tenantId: UUID,
        request: UpdateTenantRequest
    ): TenantResponse {
        val tenant = tenantRepository.findById(tenantId)
            .orElseThrow { IllegalArgumentException("Tenant not found") }

        request.name?.let {
            if (!request.name.isBlank()) {
                tenant.name = it
            }
        }
        request.phoneNumber?.let {
            if (!request.phoneNumber.isBlank()) {
                tenant.phoneNumber = it
            }
        }
        request.phoneNumber?.let {
            if (request.phoneNumber.isNotBlank()) {
                tenant.phoneNumber = it
            }
        }
        request.doorDescription?.let {
            if (!request.doorDescription.isBlank()) {
                tenant.doorDescription = it
            }
        }
        tenantRepository.save(tenant)
        return toResponse(tenant)
    }

    override fun delete(id: UUID) {
        val tenant = tenantRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Tenant not found") }

        tenant.isActive = false

        tenantRepository.save(tenant)
    }
}