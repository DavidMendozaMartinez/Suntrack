package com.davidmendozamartinez.sunrating.domain.settings.model

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class EventAlertSettings(
    val eventType: EventType,
    val isEnabled: Boolean,
    val advance: Duration,
    val qualityThreshold: Float,
)

@Suppress("ConstPropertyName")
object EventAlertSettingsDefaults {
    const val DefaultIsEnabled: Boolean = false
    val DefaultAdvance: Duration = 10.minutes
    const val DefaultQualityThreshold: Float = 2.5f
}
