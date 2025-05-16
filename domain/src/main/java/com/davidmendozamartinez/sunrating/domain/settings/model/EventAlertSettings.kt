package com.davidmendozamartinez.sunrating.domain.settings.model

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class EventAlertSettings(
    val advance: Duration,
    val qualityThreshold: Float?,
)

object EventAlertSettingsDefaults {
    val DefaultAdvance: Duration = 10.minutes
    val DefaultQualityThreshold: Float? = null
}
