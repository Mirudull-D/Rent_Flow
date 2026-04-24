package com.kotlin.rent_flow.entiites

import com.kotlin.rent_flow.entiites.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "buildings")
open class Building (
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?= null,

    var name : String,

    var address : String,

    var waterTaxAmount : BigDecimal,

    var propertyTaxAmount : BigDecimal,

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    var units: MutableList<PropertyUnit> = mutableListOf()

): BaseEntity()