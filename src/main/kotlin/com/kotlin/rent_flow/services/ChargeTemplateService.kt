package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.request.CreateChargeTemplateRequest
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse

interface ChargeTemplateService {
    fun create(request: CreateChargeTemplateRequest): ChargeTemplateResponse
}