package com.kotlin.rent_flow.mappers

import com.kotlin.rent_flow.dtos.response.TenantChargeResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.entiites.Charge
import com.kotlin.rent_flow.entiites.Tenant

object TenantMappers {
    fun toResponse(tenant: Tenant): TenantResponse {
        return TenantResponse(
            id = tenant.id!!,
            name = tenant.name,
            phoneNumber = tenant.phoneNumber,
            doorDescription = tenant.doorDescription,
            rent = tenant.rent,
            isActive = tenant.isActive
        )
    }

    fun toChargesTemplate(charges: MutableList<Charge>): List<TenantChargeResponse> {
        return charges.map { charge ->
            TenantChargeResponse(
                id = charge.id!!,
                chargeType = charge.chargeType,
                label = charge.label,
                amount = charge.amount,
                status = charge.status,
                dueDate = charge.dueDate,
                paidDate = charge.paidDate,
                isOverdue = charge.isOverdue,
                periodLabel = charge.periodLabel,
            )
        }

    }
}