package com.kotlin.rent_flow.repositories

import com.kotlin.rent_flow.dtos.response.BuildingDetailResponse
import com.kotlin.rent_flow.entiites.ChargeTemplate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ChargeTemplateRepository: JpaRepository<ChargeTemplate, UUID> {
    fun findAllByBuilding_IdAndIsActiveTrue(buildingId: UUID): List<ChargeTemplate>
}