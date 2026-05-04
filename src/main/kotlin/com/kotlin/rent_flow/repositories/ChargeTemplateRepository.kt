package com.kotlin.rent_flow.repositories

import com.kotlin.rent_flow.dtos.response.BuildingDetailResponse
import com.kotlin.rent_flow.entiites.ChargeTemplate
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChargeTemplateRepository: JpaRepository<ChargeTemplate, UUID> {
    fun findAllByBuilding_IdAndIsActiveTrue(buildingId: UUID): List<ChargeTemplate>
}