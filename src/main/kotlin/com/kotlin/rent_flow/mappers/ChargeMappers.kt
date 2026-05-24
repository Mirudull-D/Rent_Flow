package com.kotlin.rent_flow.mappers

import com.kotlin.rent_flow.dtos.response.ChargeBuildingResponse
import com.kotlin.rent_flow.dtos.response.ChargeDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateInfoResponse
import com.kotlin.rent_flow.dtos.response.ChargeTenantResponse
import com.kotlin.rent_flow.entiites.Charge

object ChargeMappers {
    fun mapToChargeResponse(charge: Charge): ChargeResponse {
        return ChargeResponse(
            id = charge.id!!,
            tenantName = charge.tenant?.name,
            buildingName = charge.building?.name,
            label = charge.label,
            amount = charge.amount,
            status = charge.status,
            chargeType = charge.chargeType,
            dueDate = charge.dueDate,
        )
    }
    fun toChargeDetailResponse(
        charge: Charge
    ): ChargeDetailResponse {

        return ChargeDetailResponse(

            id = charge.id!!,

            scope = charge.scope,

            chargeType = charge.chargeType,

            label = charge.label,

            amount = charge.amount,

            status = charge.status,

            periodLabel = charge.periodLabel,

            periodMonth = charge.periodMonth,

            periodYear = charge.periodYear,

            dueDate = charge.dueDate,

            paidDate = charge.paidDate,

            isOverdue = charge.isOverdue,

            notes = charge.notes,

            receiptS3Key = charge.receiptS3Key,

            building = charge.building?.let {
                ChargeBuildingResponse(
                    id = it.id!!,
                    name = it.name,
                    address = it.address
                )
            },

            tenant = charge.tenant?.let {
                ChargeTenantResponse(
                    id = it.id!!,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    unitNumber = it.propertyUnit.unitNumber
                )
            },

            template = charge.template?.let {
                ChargeTemplateInfoResponse(
                    id = it.id!!,
                    label = it.label,
                    frequency = it.frequency
                )
            }
        )
    }
}