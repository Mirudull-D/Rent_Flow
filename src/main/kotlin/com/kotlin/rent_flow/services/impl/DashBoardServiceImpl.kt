package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.dtos.response.DashBoardSummary
import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.repositories.BuildingRepository
import com.kotlin.rent_flow.repositories.ChargeRepository
import com.kotlin.rent_flow.repositories.PropertyUnitRepository
import com.kotlin.rent_flow.repositories.TenantRepository
import com.kotlin.rent_flow.services.DashboardService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class DashBoardServiceImpl(
    private val buildingRepository: BuildingRepository,
    private val propertyUnitRepository: PropertyUnitRepository,
    private val tenantRepository: TenantRepository,
    private val chargeRepository: ChargeRepository
): DashboardService {
    @Transactional
    override fun getSummary(): DashBoardSummary {
        val totalBuildings = buildingRepository.count()

        val units = propertyUnitRepository.findAll()
        val totalUnits = units.size.toLong()

        val occupiedUnits = units.count { it.isOccupied }
        val vacantUnits = totalUnits.toInt() - occupiedUnits

        val activeTenants =
            tenantRepository.countByIsActiveTrue()

        val charges = chargeRepository.findAll()

        val totalCharges = charges.size.toLong()

        val pendingCharges =
            charges.count {
                it.status == ChargeStatus.PENDING
            }

        val paidCharges =
            charges.count {
                it.status == ChargeStatus.PAID
            }

        val overdueCharges =
            charges.count {
                it.isOverdue
            }

        val totalPendingAmount =
            charges
                .filter {
                    it.status == ChargeStatus.PENDING
                }
                .mapNotNull { it.amount }
                .fold(BigDecimal.ZERO, BigDecimal::add)

        val totalCollectedAmount =
            charges
                .filter {
                    it.status == ChargeStatus.PAID
                }
                .mapNotNull { it.amount }
                .fold(BigDecimal.ZERO, BigDecimal::add)

        val totalOverdueAmount =
            charges
                .filter {
                    it.isOverdue
                }
                .mapNotNull { it.amount }
                .fold(BigDecimal.ZERO, BigDecimal::add)

        return DashBoardSummary(
            totalBuildings = totalBuildings,
            totalUnits = totalUnits,
            occupiedUnits = occupiedUnits,
            vacantUnits = vacantUnits,
            activeTenants = activeTenants,
            totalCharges = totalCharges,
            pendingCharges = pendingCharges,
            paidCharges = paidCharges,
            overdueCharges = overdueCharges,
            totalPendingAmount = totalPendingAmount,
            totalCollectedAmount = totalCollectedAmount,
            totalOverdueAmount = totalOverdueAmount
        )
    }
}