package com.davidmendozamartinez.sunrating.feature.settings.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.settings.component.AnimatedSettingsWarningContent
import com.davidmendozamartinez.sunrating.feature.settings.component.AnimatedSettingsWarningVisibility
import com.davidmendozamartinez.sunrating.feature.settings.component.EventAlertSettings
import com.davidmendozamartinez.sunrating.feature.settings.component.SettingsWarning
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsWarningUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.preview.buildFakeEventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedButton
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SettingsSuccessState(
    uiState: SettingsUiState.Success,
    onWarningActionResult: (SettingsWarningUiState) -> Unit,
    onEventAlertEnableCheckedChange: (EventAlertSettingsTypeUiState, Boolean) -> Unit,
    onEventAlertAdvanceItemClick: (EventAlertSettingsTypeUiState, AdvanceUiState) -> Unit,
    onEventAlertQualityThresholdValueChange: (EventAlertSettingsTypeUiState, Float) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        AnimatedSettingsWarningVisibility(value = uiState.displayedWarning) { targetState ->
            AnimatedSettingsWarningContent(targetState = targetState) {
                SettingsWarning(
                    uiState = it,
                    onActionResult = { onWarningActionResult(it) },
                    modifier = Modifier
                        .padding(top = SunRatingTheme.spacing.space4)
                        .padding(horizontal = SunRatingTheme.spacing.space4),
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(weight = 1f),
            contentPadding = PaddingValues(all = SunRatingTheme.spacing.space4),
            verticalArrangement = Arrangement.spacedBy(space = SunRatingTheme.spacing.space8),
        ) {
            items(items = uiState.items) { itemUiState ->
                EventAlertSettings(
                    uiState = itemUiState,
                    onEnableCheckedChange = { onEventAlertEnableCheckedChange(itemUiState.typeUiState, it) },
                    onAdvanceItemClick = { onEventAlertAdvanceItemClick(itemUiState.typeUiState, it) },
                    onQualityThresholdValueChange = { onEventAlertQualityThresholdValueChange(itemUiState.typeUiState, it) },
                )
            }
        }

        ThemedButton(
            text = stringResource(id = R.string.settings_button_save),
            onClick = onSaveClick,
            modifier = Modifier
                .padding(all = SunRatingTheme.spacing.space4)
                .fillMaxWidth(),
            enabled = uiState.isSaveButtonEnabled,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SettingsSuccessStatePreview(
    @PreviewParameter(provider = SettingsSuccessStatePreviewParameterProvider::class) uiState: SettingsUiState.Success,
) {
    SunRatingTheme {
        SettingsSuccessState(
            uiState = uiState,
            onWarningActionResult = {},
            onEventAlertEnableCheckedChange = { _, _ -> },
            onEventAlertAdvanceItemClick = { _, _ -> },
            onEventAlertQualityThresholdValueChange = { _, _ -> },
            onSaveClick = {},
        )
    }
}

private class SettingsSuccessStatePreviewParameterProvider : PreviewParameterProvider<SettingsUiState.Success> {
    override val values: Sequence<SettingsUiState.Success>
        get() = sequenceOf(
            SettingsUiState.Success(
                warnings = persistentListOf(
                    SettingsWarningUiState.NotificationsPermission,
                    SettingsWarningUiState.ExactAlarm,
                ),
                items = persistentListOf(
                    buildFakeEventAlertSettingsUiState(typeUiState = EventAlertSettingsTypeUiState.SUNRISE, isEnabled = false),
                    buildFakeEventAlertSettingsUiState(typeUiState = EventAlertSettingsTypeUiState.SUNSET, isEnabled = true),
                ),
                isSaveButtonEnabled = true,
            ),
        )
}
