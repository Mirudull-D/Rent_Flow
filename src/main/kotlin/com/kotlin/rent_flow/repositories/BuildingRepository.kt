package com.kotlin.rent_flow.repositories

import com.kotlin.rent_flow.entiites.Building
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BuildingRepository : JpaRepository<Building, UUID> {
    @Query("""
    SELECT b FROM Building b
    LEFT JOIN FETCH b.units u
    LEFT JOIN FETCH u.tenants
    WHERE b.id = :id
""")
    fun findByIdWithUnitsAndTenants(@Param("id") id: UUID): Building?
}