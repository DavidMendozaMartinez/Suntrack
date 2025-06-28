package com.davidmendozamartinez.sunrating.feature.events.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.feature.events.model.EventPagerUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.events.model.preview.buildFakeEventPagerPageUiState
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun EventPager(
    uiState: EventPagerUiState,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(initialPage = uiState.initialPage, pageCount = { uiState.pageCount }),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(state = pagerState) { page ->
            if (page < uiState.pages.size) EventPagerPage(uiState = uiState.pages[page])
        }

        if (uiState.pageCount > 1) {
            PageIndicator(
                pageCount = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier.padding(top = SunRatingTheme.spacing.space20),
            )
        }
    }
}

@Composable
private fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = SunRatingTheme.spacing.space2),
    ) {
        repeat(times = pageCount) { iteration ->
            val alpha: Float by animateFloatAsState(
                targetValue = if (currentPage == iteration) 1f else 0f,
                label = "PageIndicatorAlphaAnimation",
            )

            Spacer(
                modifier = Modifier
                    .size(size = PageIndicatorItemSize)
                    .clip(shape = SunRatingTheme.shape.full)
                    .border(
                        width = PageIndicatorItemBorderWidth,
                        color = SunRatingTheme.colorScheme.onBackground,
                        shape = SunRatingTheme.shape.full,
                    )
                    .background(color = SunRatingTheme.colorScheme.onBackground.copy(alpha = alpha)),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun EventPagerPreview(
    @PreviewParameter(provider = EventPagerPreviewParameterProvider::class) uiState: EventPagerUiState,
) {
    SunRatingTheme {
        EventPager(
            uiState = uiState,
        )
    }
}

private class EventPagerPreviewParameterProvider : PreviewParameterProvider<EventPagerUiState> {
    override val values: Sequence<EventPagerUiState>
        get() = sequenceOf(
            EventPagerUiState(
                initialPage = 0,
                pages = List(size = 2) {
                    buildFakeEventPagerPageUiState(eventTypeUiState = EventTypeUiState.entries[it % 2])
                }.toImmutableList(),
            ),
        )
}

private val PageIndicatorItemSize: Dp = 8.dp
private val PageIndicatorItemBorderWidth: Dp = 1.dp
