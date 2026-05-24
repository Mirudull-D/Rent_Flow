package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.request.CreateChargeTemplateRequest
import com.kotlin.rent_flow.dtos.request.UpdateChargeTemplateRequest
import com.kotlin.rent_flow.dtos.response.ChargeTemplateDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse
import com.kotlin.rent_flow.entiites.Building
import com.kotlin.rent_flow.entiites.ChargeTemplate
import com.kotlin.rent_flow.entiites.Tenant
import com.kotlin.rent_flow.enums.ScopeType
import com.kotlin.rent_flow.repositories.BuildingRepository
import com.kotlin.rent_flow.repositories.ChargeTemplateRepository
import com.kotlin.rent_flow.repositories.TenantRepository
import com.kotlin.rent_flow.services.ChargeTemplateService
import com.kotlin.rent_flow.mappers.Mappers.mapToChargeTemplateResponse
import com.kotlin.rent_flow.mappers.ChargeTemplateMappers.mapToDetailChargeTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ChargeTemplateServiceImpl(
    private val chargeTemplateRepository: ChargeTemplateRepository,
    private val buildingRepository: BuildingRepository,
    private val tenantRepository: TenantRepository
) : ChargeTemplateService {
    override fun create(
        request: CreateChargeTemplateRequest)
    : ChargeTemplateResponse {

        var building: Building? = null
        var tenant: Tenant? = null
        when (request.scope) {

            ScopeType.BUILDING -> {

                val buildingId = request.buildingId
                    ?: throw IllegalArgumentException(
                        "buildingId is required for BUILDING scope"
                    )

                building = buildingRepository.findById(buildingId)
                    .orElseThrow {
                        IllegalArgumentException("Building not found")
                    }
            }

            ScopeType.TENANT -> {

                val tenantId = request.tenantId
                    ?: throw IllegalArgumentException(
                        "tenantId is required for TENANT scope"
                    )

                tenant = tenantRepository.findById(tenantId)
                    .orElseThrow {
                        IllegalArgumentException("Tenant not found")
                    }
            }
        }

        val chargeTemplate = ChargeTemplate(
            scope = request.scope,
            building = building ,
            tenant = tenant ,
            chargeType = request.chargeType,
            label = request.label,
            defaultAmount = request.defaultAmount,
            amountIsFixed = request.amountIsFixed,
            frequency = request.frequency,
            dueDay = request.dueDay,
            autoGenerate = request.autoGenerate,
            startDate = request.startDate,
            endDate = request.endDate,
            isActive = request.isActive,
        )
        chargeTemplateRepository.save(chargeTemplate)

        return mapToChargeTemplateResponse(chargeTemplate)
    }

    override fun getAll(): List<ChargeTemplateResponse> {
        val charges = chargeTemplateRepository.findAll()
        return charges.map { mapToChargeTemplateResponse(it) }
    }

    override fun getById(id: UUID): ChargeTemplateDetailResponse {
        val template = chargeTemplateRepository.findById(id)
                .orElseThrow { IllegalArgumentException("chargeTemplate not found") }
        return mapToDetailChargeTemplate(template)
    }

    override fun getByBuilding(buildingId: UUID): List<ChargeTemplateResponse> {
        val template = chargeTemplateRepository.findAllByBuildingId(buildingId)

        return template.map { template -> mapToChargeTemplateResponse(template) }
    }

    override fun getByTenant(tenantId: UUID): List<ChargeTemplateResponse> {
        val template = chargeTemplateRepository.findAllByTenantId(tenantId)

        return template.map { template -> mapToChargeTemplateResponse(template) }

    }

    override fun update(
        id: UUID,
        request: UpdateChargeTemplateRequest
    ): ChargeTemplateDetailResponse {
        val template = chargeTemplateRepository.findById(id)
            .orElseThrow { IllegalArgumentException("chargeTemplate not found") }

        request.amountIsFixed?.let { template.amountIsFixed = it }
        request.frequency?.let { template.frequency = it }
        request.dueDay?.let { template.dueDay = it }
        request.startDate?.let { template.startDate = it }
        request.endDate?.let { template.endDate = it }
        request.autoGenerate?.let { template.autoGenerate = it }
        request.defaultAmount?.let { template.defaultAmount = it }
        request.label?.let { template.label = it }

        chargeTemplateRepository.save(template)
        return mapToDetailChargeTemplate(template)

    }

    override fun deactivate(id: UUID) {
        val template = chargeTemplateRepository.findById(id)
            .orElseThrow { IllegalArgumentException("chargeTemplate not found") }
        template.isActive = false
        chargeTemplateRepository.save(template)
    }
    override fun activate(id: UUID) {
        val template = chargeTemplateRepository.findById(id)
            .orElseThrow { IllegalArgumentException("chargeTemplate not found") }
        template.isActive = true
        chargeTemplateRepository.save(template)
    }
}