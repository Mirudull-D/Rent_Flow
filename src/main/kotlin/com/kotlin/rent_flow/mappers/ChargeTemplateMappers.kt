package com.kotlin.rent_flow.mappers

import com.kotlin.rent_flow.dtos.response.ChargeTemplateBuildingResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateChargeResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateTenantResponse
import com.kotlin.rent_flow.entiites.ChargeTemplate

object ChargeTemplateMappers {
    fun mapToDetailChargeTemplate(template: ChargeTemplate): ChargeTemplateDetailResponse{
        return ChargeTemplateDetailResponse(
            id = template.id!!,
            scope = template.scope,
            building = template.building?.let {
                ChargeTemplateBuildingResponse(
                    id = it.id!!,
                    name = it.name
                )
            },
            tenant = template.tenant?.let {
                ChargeTemplateTenantResponse(
                    id = it.id!!,
                    name = it.name
                )
            },
            chargeType = template.chargeType,
            label = template.label,
            defaultAmount = template.defaultAmount,
            amountIsFixed = template.amountIsFixed,
            frequency = template.frequency,
            dueDay = template.dueDay,
            autoGenerate = template.autoGenerate,
            startDate = template.startDate,
            endDate = template.endDate,
            isActive = template.isActive,
            charges = template.charges.map { charge ->

                ChargeTemplateChargeResponse(
                    id = charge.id!!,
                    chargeType = charge.chargeType,
                    label = charge.label,
                    amount = charge.amount,
                    status = charge.status,
                    dueDate = charge.dueDate,
                    paidDate = charge.paidDate,
                    periodLabel = charge.periodLabel,
                    isOverdue = charge.isOverdue
                )
            }
        )
    }
}