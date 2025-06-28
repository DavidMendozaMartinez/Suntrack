package com.davidmendozamartinez.sunrating.feature.events.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.davidmendozamartinez.sunrating.common.extension.format
import com.davidmendozamartinez.sunrating.common.extension.toLocalDate
import com.davidmendozamartinez.sunrating.common.extension.toLocalTime
import com.davidmendozamartinez.sunrating.common.extension.today
import com.davidmendozamartinez.sunrating.common.extension.tomorrow
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.ui.R
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBarColors
import com.davidmendozamartinez.sunrating.ui.component.custom.StarRatingBarDefaults
import com.davidmendozamartinez.sunrating.ui.designsystem.SunRatingTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

data class EventPagerUiState(
    val initialPage: Int,
    val pages: ImmutableList<EventPagerPageUiState>,
) {
    val pageCount: Int = pages.size
}

data class EventPagerPageUiState(
    val id: String,
    val time: Instant,
    val eventTypeUiState: EventTypeUiState,
    val quality: Float,
    val qualityScale: Int,
    val hasAlarm: Boolean,
) {
    val title: String
        @Composable get() = eventTypeUiState.displayName

    val formattedDate: String
        @Composable get() = when (val date: LocalDate = time.toLocalDate()) {
            Clock.System.today() -> stringResource(id = R.string.format_date_today)
            Clock.System.tomorrow() -> stringResource(id = R.string.format_date_tomorrow)
            else -> date.format()
        }

    val formattedTime: String
        @Composable get() = time.toLocalTime().format()

    val colors: EventPagerPageColors
        @Composable get() = when (eventTypeUiState) {
            EventTypeUiState.SUNRISE -> EventPagerPageColors.PrimaryColors
            EventTypeUiState.SUNSET -> EventPagerPageColors.TertiaryColors
        }
}

@Immutable
sealed interface EventPagerPageColors {
    @get:Composable
    val titleColor: Color

    @get:Composable
    val starRatingBarColors: StarRatingBarColors

    @get:Composable
    val alarmColor: Color

    data object PrimaryColors : EventPagerPageColors {
        override val titleColor: Color @Composable get() = SunRatingTheme.colorScheme.onPrimaryContainer
        override val starRatingBarColors: StarRatingBarColors @Composable get() = StarRatingBarDefaults.primaryColors()
        override val alarmColor: Color @Composable get() = SunRatingTheme.colorScheme.primary
    }

    data object TertiaryColors : EventPagerPageColors {
        override val titleColor: Color @Composable get() = SunRatingTheme.colorScheme.onTertiaryContainer
        override val starRatingBarColors: StarRatingBarColors @Composable get() = StarRatingBarDefaults.tertiaryColors()
        override val alarmColor: Color @Composable get() = SunRatingTheme.colorScheme.tertiary
    }
}

fun Event.toEventPagerPageUiState(): EventPagerPageUiState = EventPagerPageUiState(
    id = id,
    time = time,
    eventTypeUiState = type.toEventTypeUiState(),
    quality = quality,
    qualityScale = Event.QUALITY_SCALE,
    hasAlarm = alarm != null,
)

private fun EventType.toEventTypeUiState(): EventTypeUiState = when (this) {
    EventType.SUNRISE -> EventTypeUiState.SUNRISE
    EventType.SUNSET -> EventTypeUiState.SUNSET
}
