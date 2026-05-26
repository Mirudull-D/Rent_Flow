package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.response.ChargeDetailResponse
import com.kotlin.rent_flow.dtos.response.ChargeResponse
import com.kotlin.rent_flow.dtos.response.CreateManualChargeRequest
import java.util.UUID

interface ChargeServices {
    fun getAll(): List<ChargeResponse>
    fun getById(id: UUID): ChargeDetailResponse
    fun getByTenant(tenantId: UUID): List<ChargeResponse>
    fun getByBuilding(buildingId: UUID): List<ChargeResponse>
    fun createManualCharge(request: CreateManualChargeRequest): ChargeResponse
    fun markPaid(id: UUID): ChargeDetailResponse
    fun markPending(id: UUID): ChargeDetailResponse
}