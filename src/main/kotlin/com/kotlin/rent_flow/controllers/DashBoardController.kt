package com.kotlin.rent_flow.controllers

import com.kotlin.rent_flow.dtos.response.DashBoardSummary
import com.kotlin.rent_flow.services.DashboardService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dashboard")
class DashBoardController(
    private val dashboardService: DashboardService
) {

    @GetMapping("/summary")
    fun getSummary(): DashBoardSummary {
        return dashboardService.getSummary()
    }
}