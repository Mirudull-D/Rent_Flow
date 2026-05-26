package com.kotlin.rent_flow.services

import com.kotlin.rent_flow.dtos.response.DashBoardSummary

interface DashboardService {
    fun getSummary(): DashBoardSummary
}