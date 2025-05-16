package com.davidmendozamartinez.sunrating.feature.settings.model

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    data class Success(
        val eventAlertSettings: ImmutableList<EventAlertSettingsUiState>,
    ) : SettingsUiState
}

data class EventAlertSettingsUiState(
    val eventType: EventTypeUiState,
    val isEnabled: Boolean,
    val advances: ImmutableList<AdvanceUiState>,
    val selectedAdvance: AdvanceUiState,
    val qualityThreshold: Float,
)

enum class EventTypeUiState {
    SUNRISE,
    SUNSET
}

enum class AdvanceUiState(val duration: Duration) {
    TEN_MINUTES(duration = 10.minutes),
    THIRTY_MINUTES(duration = 30.minutes),
    ONE_HOUR(duration = 1.hours);

    companion object {
        fun from(duration: Duration): AdvanceUiState? = entries.find { it.duration == duration }
    }
}

fun EventAlertSettings.toEventAlertSettingsUiState(): EventAlertSettingsUiState = EventAlertSettingsUiState(
    eventType = eventType.toEventTypeUiState(),
    isEnabled = isEnabled,
    advances = AdvanceUiState.entries.toImmutableList(),
    selectedAdvance = AdvanceUiState.from(duration = advance) ?: AdvanceUiState.TEN_MINUTES,
    qualityThreshold = qualityThreshold,
)

private fun EventType.toEventTypeUiState(): EventTypeUiState = when (this) {
    EventType.SUNRISE -> EventTypeUiState.SUNRISE
    EventType.SUNSET -> EventTypeUiState.SUNSET
}

fun EventAlertSettingsUiState.toEventAlertSettings(): EventAlertSettings = EventAlertSettings(
    eventType = eventType.toEventType(),
    isEnabled = isEnabled,
    advance = selectedAdvance.duration,
    qualityThreshold = qualityThreshold,
)

private fun EventTypeUiState.toEventType(): EventType = when (this) {
    EventTypeUiState.SUNRISE -> EventType.SUNRISE
    EventTypeUiState.SUNSET -> EventType.SUNSET
}
