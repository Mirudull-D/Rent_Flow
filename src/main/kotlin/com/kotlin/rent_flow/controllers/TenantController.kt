package com.kotlin.rent_flow.controllers

import com.kotlin.rent_flow.dtos.request.CreateTenantRequest
import com.kotlin.rent_flow.dtos.request.UpdateTenantRequest
import com.kotlin.rent_flow.dtos.response.TenantDetailResponse
import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.services.TenantServices
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class TenantController (
    private val tenantService: TenantServices,
){

    @GetMapping("/units/{unitId}/tenants")
    fun getByUnit(
        @PathVariable unitId: UUID
    ): List<TenantResponse> {
        return tenantService.getByUnit(unitId)
    }

    @GetMapping("/tenants/{id}")
    fun getById(
        @PathVariable id: UUID
    ): TenantDetailResponse {
        return tenantService.getById(id)
    }

    @PostMapping("/units/{unitId}/tenants")
    fun create(
        @PathVariable unitId: UUID,
        @Valid @RequestBody request: CreateTenantRequest
    ): TenantResponse {
        return tenantService.create(unitId, request)
    }

    @PutMapping("/tenants/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: UpdateTenantRequest
    ): TenantResponse {
        return tenantService.update(id, request)
    }

    @DeleteMapping("/tenants/{id}")
    fun delete(
        @PathVariable id: UUID
    ) {
        tenantService.delete(id)
    }

}