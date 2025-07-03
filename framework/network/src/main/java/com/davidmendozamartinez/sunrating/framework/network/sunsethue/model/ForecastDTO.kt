package com.davidmendozamartinez.sunrating.framework.network.sunsethue.model

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.datetime.Clock
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

fun EventDTO.toEvent(place: Place): Event? = try {
    Event(
        id = Event.generateUUID(placeId = place.id, time = time),
        place = place,
        time = Instant.parse(input = time).also { require(value = it > Clock.System.now()) },
        type = type.toEventType(),
        quality = requireNotNull(value = quality) * Event.QUALITY_SCALE,
        alarm = null,
    )
} catch (expected: IllegalArgumentException) {
    null
}

private fun EventTypeDTO.toEventType(): EventType = when (this) {
    EventTypeDTO.SUNRISE -> EventType.SUNRISE
    EventTypeDTO.SUNSET -> EventType.SUNSET
}
