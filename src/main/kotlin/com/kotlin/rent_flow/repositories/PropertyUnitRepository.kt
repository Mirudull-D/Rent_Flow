package com.kotlin.rent_flow.repositories

import com.kotlin.rent_flow.entiites.PropertyUnit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface PropertyUnitRepository: JpaRepository<PropertyUnit, UUID> {
    fun existsByBuilding_IdAndUnitNumber(buildingId:UUID, unitNumber:String): Boolean
    fun findAllByBuilding_Id(buildingId: UUID):List<PropertyUnit>

    @Query(
        """
            SELECT u FROM PropertyUnit u
            LEFT JOIN FETCH u.tenants
            where u.id = :id
        """
    )
    fun findByIdWithTenants(@Param("id")id: UUID):PropertyUnit?
}