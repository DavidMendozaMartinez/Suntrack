package com.davidmendozamartinez.sunrating.feature.settings.model.preview

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsUiState

fun buildFakeEventAlertSettingsUiState(
    typeUiState: EventAlertSettingsTypeUiState = EventAlertSettingsTypeUiState.SUNRISE,
    isEnabled: Boolean = true,
    advanceSettingsUiState: AdvanceSettingsUiState = AdvanceSettingsUiState(selected = AdvanceUiState.TEN_MINUTES),
    qualityThreshold: Float = 2.5f,
    qualityScale: Int = Event.QUALITY_SCALE,
): EventAlertSettingsUiState = EventAlertSettingsUiState(
    typeUiState = typeUiState,
    isEnabled = isEnabled,
    advanceSettingsUiState = advanceSettingsUiState,
    qualityThreshold = qualityThreshold,
    qualityScale = qualityScale,
)
