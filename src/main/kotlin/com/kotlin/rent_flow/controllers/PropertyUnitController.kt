package com.kotlin.rent_flow.controllers

import com.kotlin.rent_flow.dtos.request.CreateUnitRequest
import com.kotlin.rent_flow.dtos.request.UpdateUnitRequest
import com.kotlin.rent_flow.dtos.response.PropertyUnitResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponse
import com.kotlin.rent_flow.dtos.response.UnitWithTenantResponses
import com.kotlin.rent_flow.services.PropertyUnitService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class PropertyUnitController(
    private val unitService: PropertyUnitService
) {

    @PostMapping("/units")
    fun create(
        @Valid @RequestBody request: CreateUnitRequest
    ): PropertyUnitResponse {
        return unitService.create( request)
    }

    @GetMapping("/buildings/{buildingId}/units")
    fun getByBuilding(
        @PathVariable buildingId: UUID
    ): List<PropertyUnitResponse> {
        return unitService.getByBuilding(buildingId)
    }

    @GetMapping("/units/{id}")
    fun getById(@PathVariable id: UUID): UnitWithTenantResponses {
        return unitService.getById(id)
    }

    @PutMapping("/units/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody request: UpdateUnitRequest
    ): UnitWithTenantResponse {
        return unitService.update(id, request)
    }

    @DeleteMapping("/units/{id}")
    fun delete(@PathVariable id: UUID) {
        unitService.delete(id)
    }
}