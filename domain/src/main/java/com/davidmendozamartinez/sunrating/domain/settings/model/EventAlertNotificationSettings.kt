package com.davidmendozamartinez.sunrating.domain.settings.model

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class EventAlertNotificationSettings(
    val advance: Duration,
    val qualityThreshold: Float?,
)

object EventAlertNotificationSettingsDefaults {
    val DefaultAdvance: Duration = 10.minutes
    val DefaultQualityThreshold: Float? = null
}
