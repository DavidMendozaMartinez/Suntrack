package com.davidmendozamartinez.sunrating.feature.settings.model.preview

import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.QualityThresholdSettingsUiState

fun buildFakeEventAlertSettingsUiState(
    typeUiState: EventAlertSettingsTypeUiState = EventAlertSettingsTypeUiState.SUNRISE,
    isEnabled: Boolean = true,
    advanceSettingsUiState: AdvanceSettingsUiState = AdvanceSettingsUiState(selected = AdvanceUiState.TEN_MINUTES),
    qualityThresholdSettingsUiState: QualityThresholdSettingsUiState = QualityThresholdSettingsUiState(value = 2.5f),
): EventAlertSettingsUiState = EventAlertSettingsUiState(
    typeUiState = typeUiState,
    isEnabled = isEnabled,
    advanceSettingsUiState = advanceSettingsUiState,
    qualityThresholdSettingsUiState = qualityThresholdSettingsUiState,
)
