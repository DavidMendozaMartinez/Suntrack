package com.davidmendozamartinez.domain.event.model

import java.util.UUID
import kotlinx.datetime.Instant

data class Event(
    val id: String,
    val placeId: String,
    val time: Instant,
    val type: EventType,
    val quality: Float,
) {
    companion object {
        fun generateUUID(
            placeId: String,
            time: String,
        ): String = UUID.nameUUIDFromBytes("$placeId,$time".toByteArray()).toString()
    }
}

enum class EventType {
    SUNRISE,
    SUNSET
}
