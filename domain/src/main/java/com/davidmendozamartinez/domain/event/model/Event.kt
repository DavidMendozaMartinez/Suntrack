package com.davidmendozamartinez.domain.event.model

import kotlinx.datetime.Instant

data class Event(
    val placeId: String,
    val type: EventType,
    val quality: Float,
    val time: Instant,
)

enum class EventType {
    SUNRISE,
    SUNSET
}
