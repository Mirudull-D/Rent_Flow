package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.request.CreateTenantRequest
import com.kotlin.rent_flow.dtos.request.UpdateTenantRequest
import com.kotlin.rent_flow.dtos.response.TenantDetailResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.entiites.Tenant
import org.springframework.stereotype.Service
import java.util.UUID


interface TenantServices {
    fun getByUnit(unitId: UUID): List<TenantResponse>
    fun getById(id: UUID): TenantDetailResponse
    fun create(unitId: UUID,tenant: CreateTenantRequest): TenantResponse
    fun update(tenantId: UUID,request: UpdateTenantRequest): TenantResponse
    fun delete(id: UUID)
}