package com.davidmendozamartinez.sunrating.domain.settings.model

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class EventAlertSettings(
    val eventType: EventType,
    val advance: Duration,
    val qualityThreshold: Float?,
)

object EventAlertSettingsDefaults {
    val DefaultAdvance: Duration = 10.minutes
    val DefaultQualityThreshold: Float? = null
}
