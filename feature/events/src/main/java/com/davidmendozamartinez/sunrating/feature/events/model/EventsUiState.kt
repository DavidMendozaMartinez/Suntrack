package com.davidmendozamartinez.sunrating.feature.events.model

import com.davidmendozamartinez.sunrating.common.extension.toLocalDate
import com.davidmendozamartinez.sunrating.common.extension.toLocalTime
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalTime

sealed interface EventsUiState {
    val topAppBarUiState: EventsTopAppBarUiState

    data object Loading : EventsUiState {
        override val topAppBarUiState: EventsTopAppBarUiState = EventsTopAppBarUiState.Empty
    }

    data class Success(
        override val topAppBarUiState: EventsTopAppBarUiState.Success,
        val events: ImmutableList<EventUiState>,
    ) : EventsUiState
}

sealed interface EventsTopAppBarUiState {
    data object Empty : EventsTopAppBarUiState

    data class Success(val title: String) : EventsTopAppBarUiState
}

data class EventUiState(
    val dayOfWeek: DayOfWeek,
    val time: LocalTime,
    val type: EventTypeUiState,
    val quality: Float,
    val hasAlarm: Boolean,
)

enum class EventTypeUiState {
    SUNRISE,
    SUNSET
}

fun Event.toEventUiState(): EventUiState = EventUiState(
    dayOfWeek = time.toLocalDate().dayOfWeek,
    time = time.toLocalTime(),
    type = type.toEventTypeUiState(),
    quality = quality,
    hasAlarm = alarm != null,
)

private fun EventType.toEventTypeUiState(): EventTypeUiState = when (this) {
    EventType.SUNRISE -> EventTypeUiState.SUNRISE
    EventType.SUNSET -> EventTypeUiState.SUNSET
}
