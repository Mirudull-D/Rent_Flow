package com.kotlin.rent_flow.controllers

import com.kotlin.rent_flow.dtos.response.ChargeResponse
import com.kotlin.rent_flow.services.ChargeServices
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/charge")
class ChargeController(
    private val chargeService: ChargeServices
) {
    @GetMapping
    fun getAll(): List<ChargeResponse> {
        return chargeService.getAll()
    }
//
//    @GetMapping("/{id}")
//    fun getById(
//        @PathVariable id: UUID
//    ): ChargeDetailResponse {
//        return chargeService.getById(id)
//    }
//
//    @GetMapping("/tenant/{tenantId}")
//    fun getByTenant(
//        @PathVariable tenantId: UUID
//    ): List<ChargeResponse> {
//        return chargeService.getByTenant(tenantId)
//    }
//
//    @GetMapping("/building/{buildingId}")
//    fun getByBuilding(
//        @PathVariable buildingId: UUID
//    ): List<ChargeResponse> {
//        return chargeService.getByBuilding(buildingId)
//    }
//
//    @PostMapping("/manual")
//    fun createManualCharge(
//        @Valid @RequestBody request: CreateManualChargeRequest
//    ): ChargeResponse {
//        return chargeService.createManualCharge(request)
//    }
//
//    @PatchMapping("/{id}/mark-paid")
//    fun markPaid(
//        @PathVariable id: UUID
//    ): ChargeDetailResponse {
//        return chargeService.markPaid(id)
//    }
//
//    @PatchMapping("/{id}/mark-pending")
//    fun markPending(
//        @PathVariable id: UUID
//    ): ChargeDetailResponse {
//        return chargeService.markPending(id)
//    }
}