package com.davidmendozamartinez.sunrating.feature.events.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.davidmendozamartinez.sunrating.ui.R

enum class EventTypeUiState {
    SUNRISE,
    SUNSET;

    val displayName: String
        @Composable get() = when (this) {
            SUNRISE -> stringResource(id = R.string.event_type_sunrise)
            SUNSET -> stringResource(id = R.string.event_type_sunset)
        }
}
