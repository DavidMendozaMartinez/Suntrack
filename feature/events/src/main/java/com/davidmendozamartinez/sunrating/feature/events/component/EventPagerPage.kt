package com.davidmendozamartinez.sunrating.feature.events.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.davidmendozamartinez.sunrating.common.extension.format
import com.davidmendozamartinez.sunrating.feature.events.model.EventPagerPageUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventTypeUiState
import com.davidmendozamartinez.sunrating.feature.events.model.preview.buildFakeEventPagerPageUiState
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBar
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBarColors
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme

@Composable
fun EventPagerPage(
    uiState: EventPagerPageUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = uiState.formattedDate,
            color = SunRatingTheme.colorScheme.onBackground,
            style = SunRatingTheme.typography.titleLarge,
        )

        Text(
            text = uiState.title,
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space2),
            color = uiState.colors.titleColor,
            style = SunRatingTheme.typography.display,
        )

        Text(
            text = uiState.formattedTime,
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space16),
            color = SunRatingTheme.colorScheme.onBackground,
            style = SunRatingTheme.typography.displayEmphasized,
        )

        Quality(
            value = uiState.quality,
            scale = uiState.qualityScale,
            starRatingBarColors = uiState.colors.starRatingBarColors,
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space4),
        )

        AlarmIndicator(
            hasAlarm = uiState.hasAlarm,
            color = uiState.colors.alarmColor,
            modifier = Modifier.padding(top = SunRatingTheme.spacing.space10),
        )
    }
}

@Composable
private fun Quality(
    value: Float,
    scale: Int,
    starRatingBarColors: StarRatingBarColors,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = value.format(),
            color = SunRatingTheme.colorScheme.onBackground,
            style = SunRatingTheme.typography.titleLarge,
        )

        StarRatingBar(
            value = value,
            modifier = Modifier.padding(start = SunRatingTheme.spacing.space3),
            colors = starRatingBarColors,
            stars = scale,
        )
    }
}

@Composable
private fun AlarmIndicator(
    hasAlarm: Boolean,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        imageVector = if (hasAlarm) Icons.Filled.Notifications else Icons.Outlined.Notifications,
        contentDescription = stringResource(
            id = if (hasAlarm) R.string.alarm_scheduled_content_description else R.string.alarm_unscheduled_content_description,
        ),
        modifier = modifier.size(size = AlarmIndicatorSize),
        tint = color,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun EventPagerPagePreview(
    @PreviewParameter(provider = EventPagerPagePreviewParameterProvider::class) uiState: EventPagerPageUiState,
) {
    SunRatingTheme {
        EventPagerPage(
            uiState = uiState,
        )
    }
}

private class EventPagerPagePreviewParameterProvider : PreviewParameterProvider<EventPagerPageUiState> {
    override val values: Sequence<EventPagerPageUiState>
        get() = sequenceOf(
            buildFakeEventPagerPageUiState(
                eventTypeUiState = EventTypeUiState.SUNRISE,
                hasAlarm = false,
            ),
            buildFakeEventPagerPageUiState(
                eventTypeUiState = EventTypeUiState.SUNSET,
                quality = 5f,
                hasAlarm = true,
            ),
        )
}

private val AlarmIndicatorSize: Dp = 40.dp
