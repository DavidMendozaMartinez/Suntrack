@file:OptIn(ExperimentalMaterial3Api::class)

package com.davidmendozamartinez.sunrating.feature.events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.feature.events.model.EventPagerUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventsUiState
import com.davidmendozamartinez.sunrating.feature.events.model.preview.buildFakeEventPagerPageUiState
import com.davidmendozamartinez.sunrating.feature.events.state.EventsNoCurrentPlaceState
import com.davidmendozamartinez.sunrating.feature.events.state.EventsSuccessState
import com.davidmendozamartinez.sunrating.ui.component.SettingsButton
import com.davidmendozamartinez.sunrating.ui.component.custom.Logotype
import com.davidmendozamartinez.sunrating.ui.component.custom.SkylineBackground
import com.davidmendozamartinez.sunrating.ui.component.theme.ThemedTopAppBar
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun EventsScreen(
    uiState: EventsUiState,
    onCurrentPlaceClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onCreatePlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { ThemedTopAppBar(title = { Logotype() }, actions = { SettingsButton(onClick = onSettingsClick) }) },
        containerColor = SunRatingTheme.colorScheme.background,
        contentColor = SunRatingTheme.colorScheme.onBackground,
    ) { contentPadding ->
        SkylineBackground()

        Box(modifier = Modifier.padding(paddingValues = contentPadding)) {
            when (uiState) {
                is EventsUiState.Loading -> Unit
                is EventsUiState.NoCurrentPlace -> EventsNoCurrentPlaceState(
                    onCreatePlaceClick = onCreatePlaceClick,
                )

                is EventsUiState.Success -> EventsSuccessState(
                    uiState = uiState,
                    onCurrentPlaceClick = onCurrentPlaceClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun EventsScreenPreview(
    @PreviewParameter(provider = EventsScreenPreviewParameterProvider::class) uiState: EventsUiState,
) {
    SunRatingTheme {
        EventsScreen(
            uiState = uiState,
            onCurrentPlaceClick = {},
            onSettingsClick = {},
            onCreatePlaceClick = {},
        )
    }
}

private class EventsScreenPreviewParameterProvider : PreviewParameterProvider<EventsUiState> {
    override val values: Sequence<EventsUiState>
        get() = sequenceOf(
            EventsUiState.NoCurrentPlace,
            EventsUiState.Success(
                currentPlaceName = LoremIpsum(words = 2).values.first(),
                eventPagerUiState = EventPagerUiState(
                    initialPage = 0,
                    pages = List(size = 2) {
                        buildFakeEventPagerPageUiState(eventTypeUiState = EventTypeUiState.entries[it % 2])
                    }.toImmutableList(),
                ),
            ),
        )
}
