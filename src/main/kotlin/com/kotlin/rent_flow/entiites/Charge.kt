package com.kotlin.rent_flow.entiites

import com.kotlin.rent_flow.entiites.base.BaseEntity
import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.enums.ChargeType
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
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDate
import java.util.UUID


@Entity
@Table(
    name = "charges",
    uniqueConstraints = [
        UniqueConstraint(
            name = "unique_template_period",
            columnNames = ["template_id", "period_month", "period_year"]
        )
    ]
)
open class Charge(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    var template: ChargeTemplate? = null,

    @Enumerated(EnumType.STRING)
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

    var amount: java.math.BigDecimal?,

    @Enumerated(EnumType.STRING)
    var status: ChargeStatus,

    var periodLabel: String?,

    @Column(name = "period_month")
    var periodMonth: Int?,

    @Column(name = "period_year")
    var periodYear: Int?,

    var dueDate: java.time.LocalDate?,

    var paidDate: java.time.LocalDate?,

    var receiptS3Key: String?,

    @Column(columnDefinition = "TEXT")
    var notes: String?
): BaseEntity()
{
    val isOverdue: Boolean
        get() =
            status == ChargeStatus.PENDING &&
                    dueDate != null &&
                    dueDate!!.isBefore(LocalDate.now())
}