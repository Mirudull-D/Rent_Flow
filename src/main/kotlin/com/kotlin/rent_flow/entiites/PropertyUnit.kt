package com.kotlin.rent_flow.entiites

import com.kotlin.rent_flow.entiites.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "property_units")
open class PropertyUnit(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    var building: Building,

    @Column(name = "unit_number", nullable = false, length = 10)
    var unitNumber: String,

    var ebNumber : String,

    @OneToMany(mappedBy = "propertyUnit", fetch = FetchType.LAZY)
    var tenants: MutableList<Tenant> = mutableListOf()

): BaseEntity(){
    val isOccupied: Boolean
        get() = tenants.any { it.isActive }

}