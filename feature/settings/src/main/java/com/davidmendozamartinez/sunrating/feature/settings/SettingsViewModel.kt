package com.davidmendozamartinez.sunrating.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidmendozamartinez.sunrating.domain.settings.repository.SettingsRepository
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.QualityThresholdSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.toEventAlertSettings
import com.davidmendozamartinez.sunrating.feature.settings.model.toEventAlertSettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private var initialSettings: ImmutableList<EventAlertSettingsUiState> = persistentListOf()

    private val _uiState: MutableStateFlow<SettingsUiState> = MutableStateFlow(value = SettingsUiState.Loading)
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private val _navigation: MutableStateFlow<SettingsNavigation?> = MutableStateFlow(value = null)
    val navigation: StateFlow<SettingsNavigation?> = _navigation.asStateFlow()

    init {
        collectEventAlertSettingsFlow()
    }

    fun onBackClick() {
        _navigation.value = SettingsNavigation.Back
    }

    fun onEventAlertEnableCheckedChange(
        typeUiState: EventAlertSettingsTypeUiState,
        isChecked: Boolean,
    ) {
        _uiState.update {
            it.updateEventAlertSettings(typeUiState = typeUiState) { alertSettings ->
                alertSettings.copy(isEnabled = isChecked)
            }
        }
    }

    fun onEventAlertAdvanceItemClick(
        typeUiState: EventAlertSettingsTypeUiState,
        advanceUiState: AdvanceUiState,
    ) {
        _uiState.update {
            it.updateEventAlertSettings(typeUiState = typeUiState) { alertSettings ->
                alertSettings.copy(advanceSettingsUiState = AdvanceSettingsUiState(selected = advanceUiState))
            }
        }
    }

    fun onEventAlertQualityThresholdValueChange(
        typeUiState: EventAlertSettingsTypeUiState,
        value: Float,
    ) {
        _uiState.update {
            it.updateEventAlertSettings(typeUiState = typeUiState) { alertSettings ->
                alertSettings.copy(qualityThresholdSettingsUiState = QualityThresholdSettingsUiState(value = value))
            }
        }
    }

    fun onSaveClick() {
        viewModelScope.launch { saveSettings() }
    }

    fun onNavigationEventConsumed() {
        _navigation.value = null
    }

    private fun collectEventAlertSettingsFlow() {
        settingsRepository.getEventAlertSettingsFlow()
            .map { settings -> settings.map { it.toEventAlertSettingsUiState() }.toImmutableList() }
            .onEach {
                initialSettings = it

                _uiState.value = SettingsUiState.Success(
                    items = it,
                    isSaveButtonEnabled = false,
                )
            }
            .launchIn(scope = viewModelScope)
    }

    private suspend fun saveSettings() {
        val currentUiState: SettingsUiState.Success = uiState.value as? SettingsUiState.Success ?: return
        val modifiedSettings: List<EventAlertSettingsUiState> = currentUiState.items.minus(elements = initialSettings)
        settingsRepository.setEventAlertSettings(settings = modifiedSettings.map { it.toEventAlertSettings() })
    }

    private fun SettingsUiState.updateEventAlertSettings(
        typeUiState: EventAlertSettingsTypeUiState,
        transform: (EventAlertSettingsUiState) -> EventAlertSettingsUiState,
    ): SettingsUiState {
        val currentUiState: SettingsUiState.Success = this as? SettingsUiState.Success ?: return this
        val modifiedSettings: ImmutableList<EventAlertSettingsUiState> = currentUiState.items
            .map { if (it.typeUiState == typeUiState) transform(it) else it }
            .toImmutableList()

        return currentUiState.copy(
            items = modifiedSettings,
            isSaveButtonEnabled = modifiedSettings != initialSettings,
        )
    }
}
