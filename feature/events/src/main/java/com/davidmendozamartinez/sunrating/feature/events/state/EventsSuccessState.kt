package com.davidmendozamartinez.sunrating.feature.events.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.davidmendozamartinez.sunrating.feature.events.component.EventPager
import com.davidmendozamartinez.sunrating.feature.events.model.EventPagerUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventsUiState
import com.davidmendozamartinez.sunrating.feature.events.model.preview.buildFakeEventPagerPageUiState
import com.davidmendozamartinez.sunrating.ui.component.custom.DropdownField
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun EventsSuccessState(
    uiState: EventsUiState.Success,
    onCurrentPlaceClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
            .padding(vertical = SunRatingTheme.spacing.space4),
    ) {
        DropdownField(
            value = uiState.currentPlaceName,
            onClick = onCurrentPlaceClick,
            modifier = Modifier
                .padding(horizontal = SunRatingTheme.spacing.space4)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = SunRatingTheme.spacing.space4,
                vertical = SunRatingTheme.spacing.space3,
            )
        )

        EventPager(
            uiState = uiState.eventPagerUiState,
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space10),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFF2F4)
@Composable
private fun EventsSuccessStatePreview(
    @PreviewParameter(provider = EventsSuccessStatePreviewParameterProvider::class) uiState: EventsUiState.Success,
) {
    SunRatingTheme {
        EventsSuccessState(
            uiState = uiState,
            onCurrentPlaceClick = {},
        )
    }
}

private class EventsSuccessStatePreviewParameterProvider : PreviewParameterProvider<EventsUiState.Success> {
    override val values: Sequence<EventsUiState.Success>
        get() = sequenceOf(
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
