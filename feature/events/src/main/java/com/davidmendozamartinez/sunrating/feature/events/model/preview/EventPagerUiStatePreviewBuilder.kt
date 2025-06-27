package com.davidmendozamartinez.sunrating.feature.events.model.preview

import com.davidmendozamartinez.sunrating.feature.events.model.EventPagerPageUiState
import com.davidmendozamartinez.sunrating.feature.events.model.EventTypeUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

fun buildFakeEventPagerPageUiState(
    id: String = "",
    time: Instant = Clock.System.now(),
    eventTypeUiState: EventTypeUiState = EventTypeUiState.SUNRISE,
    quality: Float = 2.5f,
    qualityScale: Int = 5,
    hasAlarm: Boolean = false,
): EventPagerPageUiState = EventPagerPageUiState(
    id = id,
    time = time,
    eventTypeUiState = eventTypeUiState,
    quality = quality,
    qualityScale = qualityScale,
    hasAlarm = hasAlarm
)
