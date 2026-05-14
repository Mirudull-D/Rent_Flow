package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.entiites.Tenant
import org.springframework.stereotype.Service
import java.util.UUID


interface TenantServices {
    fun getByUnit(unitId: UUID): List<TenantResponse>
}