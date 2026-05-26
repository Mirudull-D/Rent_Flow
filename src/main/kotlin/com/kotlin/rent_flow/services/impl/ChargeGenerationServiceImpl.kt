package com.kotlin.rent_flow.services.impl

import com.kotlin.rent_flow.entiites.Charge
import com.kotlin.rent_flow.enums.ChargeStatus
import com.kotlin.rent_flow.enums.FrequencyType
import com.kotlin.rent_flow.repositories.ChargeRepository
import com.kotlin.rent_flow.repositories.ChargeTemplateRepository
import com.kotlin.rent_flow.services.ChargeGenerationService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

@Service
class ChargeGenerationServiceImpl(
    private val chargeTemplateRepository: ChargeTemplateRepository,
    private val chargeRepository: ChargeRepository
) : ChargeGenerationService {
    @Transactional
    override fun generateCharges() {

        val templates =
            chargeTemplateRepository.findAllByIsActiveTrue()

        val now = LocalDate.now()

        templates.forEach { template ->

            if (
                template.endDate != null &&
                now.isAfter(
                    template.endDate!!
                        .atZone(ZoneOffset.UTC)
                        .toLocalDate()
                )
            ) {
                return@forEach
            }

            // ✅ Calculate months difference
            val startDate =
                template.startDate
                    .atZone(ZoneOffset.UTC)
                    .toLocalDate()
                    .withDayOfMonth(1)

            val currentDate =
                now.withDayOfMonth(1)

            val monthsBetween =
                ChronoUnit.MONTHS.between(
                    startDate,
                    currentDate
                )

            val shouldGenerate = when (template.frequency) {

                FrequencyType.MONTHLY ->
                    monthsBetween % 1 == 0L

                FrequencyType.BIMONTHLY ->
                    monthsBetween % 2 == 0L

                FrequencyType.QUARTERLY ->
                    monthsBetween % 3 == 0L

                FrequencyType.HALFYEARLY ->
                    monthsBetween % 6 == 0L

                FrequencyType.YEARLY ->
                    monthsBetween % 12 == 0L

                FrequencyType.ONETIME ->
                    monthsBetween == 0L
            }

            if (!shouldGenerate) {
                return@forEach
            }

            val alreadyExists =
                chargeRepository
                    .existsByTemplate_IdAndPeriodMonthAndPeriodYear(
                        template.id!!,
                        now.monthValue,
                        now.year
                    )

            if (alreadyExists) {
                return@forEach
            }

            val charge = Charge(
                template = template,

                scope = template.scope,

                building = template.building,

                tenant = template.tenant,

                chargeType = template.chargeType,

                label = template.label,

                amount = template.defaultAmount,

                status = ChargeStatus.PENDING,

                periodLabel =
                    "${now.month.name} ${now.year}",

                periodMonth = now.monthValue,

                periodYear = now.year,

                dueDate =
                    if (template.dueDay != null) {
                        LocalDate.of(
                            now.year,
                            now.month,
                            template.dueDay!!
                        )
                    } else {
                        null
                    },

                paidDate = null,

                receiptS3Key = null,

                notes = "Auto-generated charge"
            )

            chargeRepository.save(charge)
        }
    }
}