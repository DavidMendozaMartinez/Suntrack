@file:OptIn(ExperimentalMaterial3Api::class)

package com.davidmendozamartinez.sunrating.feature.settings

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
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.preview.buildFakeEventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.state.SettingsSuccessState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.BackButton
import com.davidmendozamartinez.sunrating.ui.component.custom.SkylineBackground
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTopAppBar
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onEventAlertEnableCheckedChange: (EventAlertSettingsTypeUiState, Boolean) -> Unit,
    onEventAlertAdvanceItemClick: (EventAlertSettingsTypeUiState, AdvanceUiState) -> Unit,
    onEventAlertQualityThresholdValueChange: (EventAlertSettingsTypeUiState, Float) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
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
                    onEventAlertEnableCheckedChange = onEventAlertEnableCheckedChange,
                    onEventAlertAdvanceItemClick = onEventAlertAdvanceItemClick,
                    onEventAlertQualityThresholdValueChange = onEventAlertQualityThresholdValueChange,
                    onSaveClick = onSaveClick,
                )
            }
        }
    }
}

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
        )
    }
}

private class SettingsScreenPreviewParameterProvider : PreviewParameterProvider<SettingsUiState> {
    override val values: Sequence<SettingsUiState>
        get() = sequenceOf(
            SettingsUiState.Success(
                items = persistentListOf(
                    buildFakeEventAlertSettingsUiState(typeUiState = EventAlertSettingsTypeUiState.SUNRISE, isEnabled = false),
                    buildFakeEventAlertSettingsUiState(typeUiState = EventAlertSettingsTypeUiState.SUNSET, isEnabled = true),
                ),
                isSaveButtonEnabled = true,
            ),
        )
}
