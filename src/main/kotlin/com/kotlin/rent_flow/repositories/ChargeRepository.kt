package com.kotlin.rent_flow.repositories

import com.kotlin.rent_flow.dtos.response.ChargeDetailResponse
import com.kotlin.rent_flow.entiites.Charge
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface ChargeRepository : JpaRepository<Charge, UUID> {
    @Query("""
    SELECT c
    FROM Charge c
    LEFT JOIN FETCH c.tenant t
    LEFT JOIN FETCH t.propertyUnit pu
    LEFT JOIN FETCH pu.building
    LEFT JOIN FETCH c.template
    WHERE c.id = :id
""")
    fun findDetailedById(
        @Param("id") id: UUID
    ): Charge?

    @Query("""
    SELECT c
    FROM Charge c
    LEFT JOIN FETCH c.tenant
    LEFT JOIN FETCH c.building
""")
    fun findAllWithRelations(): List<Charge>

    fun existsByTenant_Id(tenantId: UUID): Boolean
    fun findAllByTenant_Id(tenantId: UUID): List<Charge>

    fun existsByBuilding_Id(buildingId: UUID): Boolean
    fun findAllByBuilding_Id(buildingId: UUID): List<Charge>
}