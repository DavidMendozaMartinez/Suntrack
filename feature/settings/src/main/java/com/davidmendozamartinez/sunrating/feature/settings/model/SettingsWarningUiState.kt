package com.davidmendozamartinez.sunrating.feature.settings.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.davidmendozamartinez.sunrating.ui.R

sealed interface SettingsWarningUiState {
    @get:Composable
    val iconPainter: Painter

    @get:Composable
    val message: String

    @get:Composable
    val actionLabel: String

    data object NotificationsPermission : SettingsWarningUiState {
        override val iconPainter: Painter
            @Composable get() = rememberVectorPainter(image = Icons.Outlined.NotificationsActive)
        override val message: String
            @Composable get() = stringResource(id = R.string.settings_warning_permission_notifications_message)
        override val actionLabel: String
            @Composable get() = stringResource(id = R.string.settings_warning_permission_notifications_action)
    }

    data object ExactAlarm : SettingsWarningUiState {
        override val iconPainter: Painter
            @Composable get() = rememberVectorPainter(image = Icons.Outlined.Alarm)
        override val message: String
            @Composable get() = stringResource(id = R.string.settings_warning_exact_alarm_message)
        override val actionLabel: String
            @Composable get() = stringResource(id = R.string.settings_warning_exact_alarm_action)
    }
}
