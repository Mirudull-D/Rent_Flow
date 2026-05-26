package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.response.ChargeDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeResponse
import com.kotlin.rent_flow.dtos.response.CreateManualChargeRequest
import com.kotlin.rent_flow.entiites.Building
import com.kotlin.rent_flow.entiites.Charge
import com.kotlin.rent_flow.entiites.Tenant
import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.enums.ScopeType
import com.kotlin.rent_flow.repositories.ChargeRepository
import com.kotlin.rent_flow.services.ChargeServices
import org.springframework.stereotype.Service
import com.kotlin.rent_flow.mappers.ChargeMappers.mapToChargeResponse
import com.kotlin.rent_flow.mappers.ChargeMappers.toChargeDetailResponse
import com.kotlin.rent_flow.repositories.BuildingRepository
import com.kotlin.rent_flow.repositories.TenantRepository
import jakarta.transaction.Transactional
import java.time.LocalDate
import java.util.UUID

@Service
class ChargeServicesImpl(
    private val chargeRepository: ChargeRepository,
    private val buildingRepository: BuildingRepository,
    private val tenantRepository: TenantRepository
) : ChargeServices {
    @Transactional
    override fun getAll(): List<ChargeResponse> {
        val charges = chargeRepository.findAllWithRelations()
        return charges.map { charge -> mapToChargeResponse(charge) }

    }

    override fun getById(id: UUID): ChargeDetailResponse {
        val charge = chargeRepository.findDetailedById(id)
            ?: throw IllegalArgumentException(
                "Charge not found with id $id"
            )

        return toChargeDetailResponse(charge)
    }

    override fun getByTenant(tenantId: UUID): List<ChargeResponse> {
        if (chargeRepository.existsByTenant_Id(tenantId)) {
            val charge = chargeRepository.findAllByTenant_Id(tenantId)
            return charge.map { charge -> mapToChargeResponse(charge) }
        }else{
            throw IllegalArgumentException("Tenant not found with id $tenantId")
        }
    }

    override fun getByBuilding(buildingId: UUID): List<ChargeResponse> {
        if(chargeRepository.existsByBuilding_Id(buildingId)) {
            val charges = chargeRepository.findAllByBuilding_Id(buildingId)
            return charges.map { charge -> mapToChargeResponse(charge) }
        }
        else{
            throw IllegalArgumentException("Building not found with id $buildingId")
        }
    }

    @Transactional
    override fun createManualCharge(request: CreateManualChargeRequest): ChargeResponse {
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

                tenant = tenantRepository.findById(request.tenantId)
                    .orElseThrow {
                        IllegalArgumentException("Tenant not found")
                    }
            }
        }
        val charge = Charge(
            scope = request.scope,
            building = building,
            tenant = tenant,
            chargeType = request.chargeType,
            label = request.label,
            amount = request.amount,
            periodLabel = null,
            periodMonth = null,
            periodYear =null,
            dueDate = request.dueDate,
            notes = request.notes,
            status = ChargeStatus.PENDING,
            receiptS3Key = null,
            paidDate = null,
        )

        val saved = chargeRepository.save(charge)
        return mapToChargeResponse(saved)
    }

    override fun markPaid(id: UUID): ChargeDetailResponse {
        val charge = chargeRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Charge not found with id $id") }
        if (charge.status == ChargeStatus.PAID) {
            throw IllegalArgumentException("Charge is already paid")
        }

        charge.status = ChargeStatus.PAID
        charge.paidDate = LocalDate.now()

        chargeRepository.save(charge)

        return toChargeDetailResponse(charge)
    }

    override fun markPending(id: UUID): ChargeDetailResponse {

        val charge = chargeRepository.findById(id)
            .orElseThrow {
                IllegalArgumentException("Charge not found with id $id")
            }

        charge.status = ChargeStatus.PENDING
        charge.paidDate = null

        return toChargeDetailResponse(charge)
    }


}