package com.davidmendozamartinez.sunrating.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.repository.SettingsRepository
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.toEventAlertSettings
import com.davidmendozamartinez.sunrating.feature.settings.model.toEventAlertSettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    val uiState: StateFlow<SettingsUiState> = combine(
        EventType.entries.map { settingsRepository.getEventAlertSettingsFlow(eventType = it) }
    ) { settings ->
        SettingsUiState.Success(eventAlertSettings = settings.map { it.toEventAlertSettingsUiState() }.toImmutableList())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = SettingsUiState.Loading
    )

    fun onEventAlertEnabledCheckedChange(eventType: EventTypeUiState, isEnabled: Boolean) {
        val settings: EventAlertSettingsUiState = (uiState.value as? SettingsUiState.Success)
            ?.eventAlertSettings?.find { it.eventType == eventType }
            ?: return

        viewModelScope.launch {
            settingsRepository.setEventAlertSettings(
                settings = settings
                    .toEventAlertSettings()
                    .copy(isEnabled = isEnabled),
            )
        }
    }

    fun onEventAlertAdvanceItemClick(eventType: EventTypeUiState, item: AdvanceUiState) {
        val settings: EventAlertSettingsUiState = (uiState.value as? SettingsUiState.Success)
            ?.eventAlertSettings?.find { it.eventType == eventType }
            ?: return

        viewModelScope.launch {
            settingsRepository.setEventAlertSettings(
                settings = settings
                    .toEventAlertSettings()
                    .copy(advance = item.duration),
            )
        }
    }

    fun onEventAlertQualityThresholdValueChange(eventType: EventTypeUiState, value: Float) {
        val settings: EventAlertSettingsUiState = (uiState.value as? SettingsUiState.Success)
            ?.eventAlertSettings?.find { it.eventType == eventType }
            ?: return

        viewModelScope.launch {
            settingsRepository.setEventAlertSettings(
                settings = settings
                    .toEventAlertSettings()
                    .copy(qualityThreshold = value),
            )
        }
    }
}
