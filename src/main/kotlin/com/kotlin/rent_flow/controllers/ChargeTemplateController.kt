package com.kotlin.rent_flow.controllers

import com.kotlin.rent_flow.dtos.request.CreateChargeTemplateRequest
import com.kotlin.rent_flow.dtos.response.ChargeTemplateResponse
import com.kotlin.rent_flow.services.ChargeTemplateService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/charge-templates")
class ChargeTemplateController(
    private val chargeTemplateService: ChargeTemplateService
) {

//    @PostMapping
//    fun create(
//        @Valid @RequestBody request: CreateChargeTemplateRequest
//    ): ChargeTemplateResponse {
//
//        return chargeTemplateService.create(request)
//    }
//
//    @GetMapping
//    fun getAll(): List<ChargeTemplateResponse> {
//
//        return chargeTemplateService.getAll()
//    }
//
//    @GetMapping("/{id}")
//    fun getById(
//        @PathVariable id: UUID
//    ): ChargeTemplateDetailResponse {
//
//        return chargeTemplateService.getById(id)
//    }
//
//    @GetMapping("/building/{buildingId}")
//    fun getByBuilding(
//        @PathVariable buildingId: UUID
//    ): List<ChargeTemplateResponse> {
//
//        return chargeTemplateService.getByBuilding(buildingId)
//    }
//
//    @GetMapping("/tenant/{tenantId}")
//    fun getByTenant(
//        @PathVariable tenantId: UUID
//    ): List<ChargeTemplateResponse> {
//
//        return chargeTemplateService.getByTenant(tenantId)
//    }
//
//    @PutMapping("/{id}")
//    fun update(
//        @PathVariable id: UUID,
//        @Valid @RequestBody request: UpdateChargeTemplateRequest
//    ): ChargeTemplateDetailResponse {
//
//        return chargeTemplateService.update(id, request)
//    }
//
//    @PatchMapping("/{id}/deactivate")
//    fun deactivate(
//        @PathVariable id: UUID
//    ) {
//        return chargeTemplateService.deactivate(id)
//    }
//
//    @PatchMapping("/{id}/activate")
//    fun activate(
//        @PathVariable id: UUID
//    ) {
//        chargeTemplateService.activate(id)
//    }


}
