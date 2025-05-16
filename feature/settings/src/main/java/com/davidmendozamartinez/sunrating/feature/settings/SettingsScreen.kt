package com.davidmendozamartinez.sunrating.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.feature.settings.model.AdvanceUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventAlertSettingsUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.settings.model.SettingsUiState
import java.util.Locale
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun SettingsScreen(
    uiState: SettingsUiState,
    onEventAlertEnabledCheckedChange: (EventTypeUiState, Boolean) -> Unit,
    onEventAlertAdvanceItemClick: (EventTypeUiState, AdvanceUiState) -> Unit,
    onEventAlertQualityThresholdValueChange: (EventTypeUiState, Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(insets = WindowInsets.ime),
    ) { contentPadding ->
        Box(modifier = Modifier.padding(paddingValues = contentPadding)) {
            when (uiState) {
                is SettingsUiState.Loading -> Unit
                is SettingsUiState.Success -> LazyColumn {
                    items(items = uiState.eventAlertSettings) { uiState ->
                        EventAlertSettings(
                            uiState = uiState,
                            onEnabledCheckedChange = { onEventAlertEnabledCheckedChange(uiState.eventType, it) },
                            onAdvanceItemClick = { onEventAlertAdvanceItemClick(uiState.eventType, it) },
                            onQualityThresholdValueChange = { onEventAlertQualityThresholdValueChange(uiState.eventType, it) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EventAlertSettings(
    uiState: EventAlertSettingsUiState,
    onEnabledCheckedChange: (Boolean) -> Unit,
    onAdvanceItemClick: (AdvanceUiState) -> Unit,
    onQualityThresholdValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "${uiState.eventType.name.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }} alert")

            Checkbox(
                checked = uiState.isEnabled,
                onCheckedChange = onEnabledCheckedChange,
            )
        }

        if (uiState.isEnabled) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Advance")

                var expanded: Boolean by remember { mutableStateOf(value = false) }
                Box {
                    Row(
                        modifier = Modifier
                            .clickable { expanded = true }
                            .padding(all = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = uiState.selectedAdvance.duration.toString())
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        uiState.advances.forEach {
                            DropdownMenuItem(
                                text = { Text(text = it.duration.toString()) },
                                onClick = {
                                    onAdvanceItemClick(it)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            var sliderValue: Float by remember { mutableFloatStateOf(value = uiState.qualityThreshold) }

            Text(text = "Quality threshold: ${String.format(Locale.getDefault(), "%.1f", (sliderValue * 100) / 10)}/10")
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                onValueChangeFinished = { onQualityThresholdValueChange(sliderValue) },
            )
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview(
    @PreviewParameter(provider = SettingsScreenPreviewParameterProvider::class) uiState: SettingsUiState,
) {
    SettingsScreen(
        uiState = uiState,
        onEventAlertEnabledCheckedChange = { _, _ -> },
        onEventAlertAdvanceItemClick = { _, _ -> },
        onEventAlertQualityThresholdValueChange = { _, _ -> },
    )
}

private class SettingsScreenPreviewParameterProvider : PreviewParameterProvider<SettingsUiState> {
    override val values: Sequence<SettingsUiState>
        get() = sequenceOf(
            SettingsUiState.Success(
                eventAlertSettings = persistentListOf(
                    EventAlertSettingsUiState(
                        eventType = EventTypeUiState.SUNRISE,
                        isEnabled = true,
                        advances = AdvanceUiState.entries.toImmutableList(),
                        selectedAdvance = AdvanceUiState.TEN_MINUTES,
                        qualityThreshold = 0.7f,
                    ),
                    EventAlertSettingsUiState(
                        eventType = EventTypeUiState.SUNSET,
                        isEnabled = true,
                        advances = AdvanceUiState.entries.toImmutableList(),
                        selectedAdvance = AdvanceUiState.TEN_MINUTES,
                        qualityThreshold = 0.5f,
                    ),
                ),
            ),
        )
}
