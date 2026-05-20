package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.request.CreateChargeTemplateRequest
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
import org.springframework.stereotype.Service

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
}