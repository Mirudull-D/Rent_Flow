package com.kotlin.rent_flow.entiites

import com.kotlin.rent_flow.entiites.base.BaseEntity
import com.kotlin.rent_flow.enums.ChargeType
import com.kotlin.rent_flow.enums.FrequencyType
import com.kotlin.rent_flow.enums.ScopeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "charge_templates")
open class ChargeTemplate (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var scope: ScopeType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    var building: Building? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    var tenant: Tenant? = null,

    @Enumerated(EnumType.STRING)
    var chargeType: ChargeType,

    var label: String,

    var defaultAmount: java.math.BigDecimal?,

    var amountIsFixed: Boolean,

    @Enumerated(EnumType.STRING)
    var frequency: FrequencyType,

    var dueDay: Int?,

    var autoGenerate: Boolean = true,

    var startDate: Instant,

    var endDate: Instant?,

    var isActive: Boolean = true,

    @OneToMany(mappedBy = "template", fetch = FetchType.LAZY)
    var charges: MutableList<Charge> = mutableListOf()
): BaseEntity()