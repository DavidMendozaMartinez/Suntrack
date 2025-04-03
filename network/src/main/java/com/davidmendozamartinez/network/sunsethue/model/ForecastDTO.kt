package com.davidmendozamartinez.network.sunsethue.model

import com.davidmendozamartinez.domain.event.model.Event
import com.davidmendozamartinez.domain.event.model.EventType
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDTO(
    @SerialName("data") val data: List<EventDTO>,
)

@Serializable
data class EventDTO(
    @SerialName("type") val type: EventTypeDTO,
    @SerialName("quality") val quality: Float?,
    @SerialName("time") val time: String,
)

@Serializable
enum class EventTypeDTO {
    @SerialName("sunrise")
    SUNRISE,

    @SerialName("sunset")
    SUNSET,
}

fun EventDTO.toEvent(): Event? = try {
    Event(
        type = type.toEventType(),
        quality = requireNotNull(value = quality),
        time = Instant.parse(input = time),
    )
} catch (expected: IllegalArgumentException) {
    null
}

private fun EventTypeDTO.toEventType(): EventType = when (this) {
    EventTypeDTO.SUNRISE -> EventType.SUNRISE
    EventTypeDTO.SUNSET -> EventType.SUNSET
}
