package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.request.CreateChargeTemplateRequest
import com.kotlin.rent_flow.dtos.request.UpdateChargeTemplateRequest
import com.kotlin.rent_flow.dtos.response.ChargeTemplateDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse
import java.util.UUID

interface ChargeTemplateService {
    fun create(request: CreateChargeTemplateRequest): ChargeTemplateResponse
    fun getAll(): List<ChargeTemplateResponse>
    fun getById(id: UUID): ChargeTemplateDetailResponse
    fun getByBuilding(buildingId: UUID): List<ChargeTemplateResponse>
    fun getByTenant(tenantId: UUID): List<ChargeTemplateResponse>
    fun update(id: UUID,request: UpdateChargeTemplateRequest): ChargeTemplateDetailResponse
    fun deactivate(id: UUID)
    fun activate(id: UUID)
}