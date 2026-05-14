package com.kotlin.rent_flow.mappers

import com.kotlin.rent_flow.dtos.response.TenantResponse
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
}