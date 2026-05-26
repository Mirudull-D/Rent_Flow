package com.kotlin.rent_flow.repositories

import com.kotlin.rent_flow.dtos.response.TenantResponse
import com.kotlin.rent_flow.entiites.Tenant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TenantRepository : JpaRepository<Tenant, UUID> {
    fun existsByPropertyUnit_Building_IdAndIsActiveTrue(BuildingId: UUID): Boolean
    fun existsByPropertyUnit_IdAndIsActiveTrue(propertyUnitId: UUID): Boolean
    fun findAllByPropertyUnit_Id(propertyUnitId: UUID): List<Tenant>
    fun countByIsActiveTrue(): Long
}