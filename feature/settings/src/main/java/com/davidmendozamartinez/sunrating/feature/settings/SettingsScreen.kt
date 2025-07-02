@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)

package com.davidmendozamartinez.sunrating.feature.settings

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsActionRequiredUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.preview.buildFakeEventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.state.SettingsSuccessState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.BackButton
import com.davidmendozamartinez.sunrating.ui.component.custom.SkylineBackground
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTopAppBar
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onEventAlertEnableCheckedChange: (EventAlertSettingsTypeUiState, Boolean) -> Unit,
    onEventAlertAdvanceItemClick: (EventAlertSettingsTypeUiState, AdvanceUiState) -> Unit,
    onEventAlertQualityThresholdValueChange: (EventAlertSettingsTypeUiState, Float) -> Unit,
    onSaveClick: () -> Unit,
    onNotificationsPermissionResult: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val notificationsPermissionState: PermissionState = rememberNotificationsPermissionState(
        onPermissionResult = { onNotificationsPermissionResult() },
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            ThemedTopAppBar(
                title = stringResource(id = R.string.settings_title),
                navigationIcon = { BackButton(onClick = onBackClick) }
            )
        },
        containerColor = SunRatingTheme.colorScheme.background,
        contentColor = SunRatingTheme.colorScheme.onBackground,
    ) { contentPadding ->
        SkylineBackground()

        Box(modifier = Modifier.padding(paddingValues = contentPadding)) {
            when (uiState) {
                is SettingsUiState.Loading -> Unit
                is SettingsUiState.Success -> SettingsSuccessState(
                    uiState = uiState,
                    onEventAlertEnableCheckedChange = { typeUiState, isChecked ->
                        onEventAlertEnableCheckedChange(typeUiState, isChecked)

                        if (isChecked && !notificationsPermissionState.status.isGranted) {
                            notificationsPermissionState.launchPermissionRequest()
                        }
                    },
                    onEventAlertAdvanceItemClick = onEventAlertAdvanceItemClick,
                    onEventAlertQualityThresholdValueChange = onEventAlertQualityThresholdValueChange,
                    onSaveClick = onSaveClick,
                )
            }
        }
    }
}

@SuppressLint("InlinedApi")
@Composable
private fun rememberNotificationsPermissionState(
    onPermissionResult: (Boolean) -> Unit,
): PermissionState = rememberPermissionState(
    permission = Manifest.permission.POST_NOTIFICATIONS,
    onPermissionResult = onPermissionResult,
)

@Preview
@Composable
private fun SettingsScreenPreview(
    @PreviewParameter(provider = SettingsScreenPreviewParameterProvider::class) uiState: SettingsUiState,
) {
    SunRatingTheme {
        SettingsScreen(
            uiState = uiState,
            onBackClick = {},
            onEventAlertEnableCheckedChange = { _, _ -> },
            onEventAlertAdvanceItemClick = { _, _ -> },
            onEventAlertQualityThresholdValueChange = { _, _ -> },
            onSaveClick = {},
            onNotificationsPermissionResult = {},
        )
    }
}

private class SettingsScreenPreviewParameterProvider : PreviewParameterProvider<SettingsUiState> {
    override val values: Sequence<SettingsUiState>
        get() = sequenceOf(
            SettingsUiState.Success(
                requiredActions = persistentListOf(
                    SettingsActionRequiredUiState.NotificationsPermission,
                    SettingsActionRequiredUiState.ExactAlarm,
                ),
                items = persistentListOf(
                    buildFakeEventAlertSettingsUiState(typeUiState = EventAlertSettingsTypeUiState.SUNRISE, isEnabled = false),
                    buildFakeEventAlertSettingsUiState(typeUiState = EventAlertSettingsTypeUiState.SUNSET, isEnabled = true),
                ),
                isSaveButtonEnabled = true,
            ),
        )
}
