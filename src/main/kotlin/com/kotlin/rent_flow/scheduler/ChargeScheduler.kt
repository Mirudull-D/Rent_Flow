package com.kotlin.rent_flow.scheduler

import com.kotlin.rent_flow.services.ChargeGenerationService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ChargeScheduler(
    private val chargeGenerationService: ChargeGenerationService
) {

    @Scheduled(cron = "0 0 1 * * *")
    fun runChargeGeneration() {

        chargeGenerationService.generateCharges()
    }
}