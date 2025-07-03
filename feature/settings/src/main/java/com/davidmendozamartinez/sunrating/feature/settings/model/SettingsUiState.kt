package com.davidmendozamartinez.sunrating.feature.settings.model

import kotlinx.collections.immutable.ImmutableList

sealed interface SettingsUiState {
    data object Loading : SettingsUiState

    data class Success(
        val warnings: ImmutableList<SettingsWarningUiState>,
        val items: ImmutableList<EventAlertSettingsUiState>,
        val isSaveButtonEnabled: Boolean,
    ) : SettingsUiState {
        val displayedWarning: SettingsWarningUiState? = warnings.firstOrNull()
    }
}
