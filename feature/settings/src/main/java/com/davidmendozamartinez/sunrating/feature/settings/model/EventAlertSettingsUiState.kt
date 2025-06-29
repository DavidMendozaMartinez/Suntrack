package com.davidmendozamartinez.sunrating.feature.settings.model

import androidx.compose.material3.SliderColors
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBarColors
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBarDefaults
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedSliderDefaults
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedSwitchDefaults

data class EventAlertSettingsUiState(
    val typeUiState: EventAlertSettingsTypeUiState,
    val isEnabled: Boolean,
    val advanceSettingsUiState: AdvanceSettingsUiState,
    val qualityThreshold: Float,
    val qualityScale: Int,
) {
    val title: String
        @Composable get() = typeUiState.displayName

    val colors: EventAlertSettingsColors = when (typeUiState) {
        EventAlertSettingsTypeUiState.SUNRISE -> EventAlertSettingsColors.PrimaryColors
        EventAlertSettingsTypeUiState.SUNSET -> EventAlertSettingsColors.TertiaryColors
    }
}

enum class EventAlertSettingsTypeUiState {
    SUNRISE,
    SUNSET;

    val displayName: String
        @Composable get() = when (this) {
            SUNRISE -> stringResource(id = R.string.settings_event_alert_title_sunrise)
            SUNSET -> stringResource(id = R.string.settings_event_alert_title_sunset)
        }
}

@Immutable
sealed interface EventAlertSettingsColors {
    @get:Composable
    val switchColors: SwitchColors

    @get:Composable
    val sliderColors: SliderColors

    @get:Composable
    val starRatingBarColors: StarRatingBarColors

    data object PrimaryColors : EventAlertSettingsColors {
        override val switchColors: SwitchColors @Composable get() = ThemedSwitchDefaults.primaryColors()
        override val sliderColors: SliderColors @Composable get() = ThemedSliderDefaults.primaryColors()
        override val starRatingBarColors: StarRatingBarColors @Composable get() = StarRatingBarDefaults.primaryColors()
    }

    data object TertiaryColors : EventAlertSettingsColors {
        override val switchColors: SwitchColors @Composable get() = ThemedSwitchDefaults.tertiaryColors()
        override val sliderColors: SliderColors @Composable get() = ThemedSliderDefaults.tertiaryColors()
        override val starRatingBarColors: StarRatingBarColors @Composable get() = StarRatingBarDefaults.tertiaryColors()
    }
}

fun EventAlertSettings.toEventAlertSettingsUiState(): EventAlertSettingsUiState = EventAlertSettingsUiState(
    typeUiState = eventType.toEventAlertSettingsTypeUiState(),
    isEnabled = isEnabled,
    advanceSettingsUiState = AdvanceSettingsUiState(
        selected = AdvanceUiState.from(duration = advance) ?: AdvanceUiState.TEN_MINUTES,
    ),
    qualityThreshold = qualityThreshold,
    qualityScale = Event.QUALITY_SCALE,
)

private fun EventType.toEventAlertSettingsTypeUiState(): EventAlertSettingsTypeUiState = when (this) {
    EventType.SUNRISE -> EventAlertSettingsTypeUiState.SUNRISE
    EventType.SUNSET -> EventAlertSettingsTypeUiState.SUNSET
}
