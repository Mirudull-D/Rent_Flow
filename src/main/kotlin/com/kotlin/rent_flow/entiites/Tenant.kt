package com.kotlin.rent_flow.entiites

import com.kotlin.rent_flow.entiites.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "tenants")
open class Tenant (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id",nullable = false)
    var propertyUnit: PropertyUnit,

    var isActive: Boolean = true,

    var name: String,

    var phoneNumber : String,

    var doorDescription : String,

    var rent: BigDecimal,

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    var charges: MutableList<Charge> = mutableListOf()

): BaseEntity()