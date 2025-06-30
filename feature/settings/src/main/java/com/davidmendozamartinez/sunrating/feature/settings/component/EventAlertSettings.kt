package com.davidmendozamartinez.sunrating.feature.settings.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsColors
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.QualityThresholdSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.preview.buildFakeEventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.custom.DropdownField
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBar
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedDropdownMenu
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedDropdownMenuItem
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedSlider
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedSwitch
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
internal fun EventAlertSettings(
    uiState: EventAlertSettingsUiState,
    onEnableCheckedChange: (Boolean) -> Unit,
    onAdvanceItemClick: (AdvanceUiState) -> Unit,
    onQualityThresholdValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = uiState.title,
                modifier = Modifier.weight(weight = 1f),
                color = SunRatingTheme.colorScheme.onBackground,
                style = SunRatingTheme.typography.titleMediumEmphasized,
            )

            ThemedSwitch(
                checked = uiState.isEnabled,
                onCheckedChange = onEnableCheckedChange,
                colors = uiState.colors.switchColors,
            )
        }

        AnimatedVisibility(
            visible = uiState.isEnabled,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Column(modifier = Modifier.padding(top = SunRatingTheme.spacing.space6)) {
                AdvanceSettings(
                    uiState = uiState.advanceSettingsUiState,
                    onItemClick = onAdvanceItemClick,
                )

                QualityThresholdSettings(
                    uiState = uiState.qualityThresholdSettingsUiState,
                    colors = uiState.colors,
                    onValueChange = onQualityThresholdValueChange,
                    modifier = Modifier.padding(top = SunRatingTheme.spacing.space6),
                )
            }
        }
    }
}

@Composable
private fun AdvanceSettings(
    uiState: AdvanceSettingsUiState,
    onItemClick: (AdvanceUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.settings_event_alert_advance),
            color = SunRatingTheme.colorScheme.onBackground,
            style = SunRatingTheme.typography.bodyMedium,
        )

        Box(
            modifier = Modifier
                .padding(start = SunRatingTheme.spacing.space6)
                .weight(weight = 1f),
        ) {
            var expanded by remember { mutableStateOf(value = false) }

            DropdownField(
                value = uiState.selected.displayName,
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(),
            )

            ThemedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                uiState.items.forEach {
                    ThemedDropdownMenuItem(
                        text = it.displayName,
                        onClick = {
                            expanded = false
                            onItemClick(it)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun QualityThresholdSettings(
    uiState: QualityThresholdSettingsUiState,
    colors: EventAlertSettingsColors,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.settings_event_alert_quality_threshold),
            color = SunRatingTheme.colorScheme.onBackground,
            style = SunRatingTheme.typography.bodyMedium,
        )

        ThemedSlider(
            value = uiState.value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space1),
            colors = colors.sliderColors,
            steps = uiState.sliderSteps,
            valueRange = uiState.valueRange,
        )

        Row(
            modifier = Modifier
                .padding(top = SunRatingTheme.spacing.space1)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = uiState.formattedValue,
                color = SunRatingTheme.colorScheme.onBackground,
                style = SunRatingTheme.typography.titleLarge,
            )

            StarRatingBar(
                value = uiState.value,
                modifier = Modifier.padding(start = SunRatingTheme.spacing.space3),
                colors = colors.starRatingBarColors,
                stars = uiState.scale,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFF2F4)
@Composable
private fun EventAlertSettingsPreview(
    @PreviewParameter(provider = EventAlertSettingsPreviewParameterProvider::class) uiState: EventAlertSettingsUiState,
) {
    SunRatingTheme {
        EventAlertSettings(
            uiState = uiState,
            onEnableCheckedChange = {},
            onAdvanceItemClick = {},
            onQualityThresholdValueChange = {},
        )
    }
}

private class EventAlertSettingsPreviewParameterProvider : PreviewParameterProvider<EventAlertSettingsUiState> {
    override val values: Sequence<EventAlertSettingsUiState>
        get() = sequenceOf(
            buildFakeEventAlertSettingsUiState(isEnabled = false),
            buildFakeEventAlertSettingsUiState(isEnabled = true),
        )
}
