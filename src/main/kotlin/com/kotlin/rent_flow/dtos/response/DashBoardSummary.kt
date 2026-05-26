package com.kotlin.rent_flow.dtos.response

import java.math.BigDecimal

data class DashBoardSummary (

val totalBuildings: Long,

val totalUnits: Long,

val occupiedUnits: Int,

val vacantUnits: Int,

val activeTenants: Long,

val totalCharges: Long,

val pendingCharges: Int,

val paidCharges: Int,

val overdueCharges: Int,

val totalPendingAmount: BigDecimal,

val totalCollectedAmount: BigDecimal,

val totalOverdueAmount: BigDecimal
)