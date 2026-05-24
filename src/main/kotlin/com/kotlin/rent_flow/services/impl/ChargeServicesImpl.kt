package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.response.ChargeDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeResponse
import com.kotlin.rent_flow.repositories.ChargeRepository
import com.kotlin.rent_flow.services.ChargeServices
import org.springframework.stereotype.Service
import com.kotlin.rent_flow.mappers.ChargeMappers.mapToChargeResponse
import com.kotlin.rent_flow.mappers.ChargeMappers.toChargeDetailResponse
import jakarta.transaction.Transactional
import java.util.UUID

@Service
class ChargeServicesImpl(
    private val chargeRepository: ChargeRepository
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
        val charge = chargeRepository.findAllByTenant_Id(tenantId)
        return charge.map { charge -> mapToChargeResponse(charge) }
    }

}