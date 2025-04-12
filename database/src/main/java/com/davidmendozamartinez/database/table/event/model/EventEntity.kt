package com.davidmendozamartinez.database.table.event.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.davidmendozamartinez.domain.event.model.Event
import com.davidmendozamartinez.domain.event.model.EventType
import kotlinx.datetime.Instant

@Entity(tableName = "event", primaryKeys = ["place_id", "time_millis"])
data class EventEntity(
    @ColumnInfo(name = "place_id") val placeId: String,
    @ColumnInfo(name = "time_millis") val timeMillis: Long,
    @ColumnInfo(name = "type") val type: EventTypeEntity,
    @ColumnInfo(name = "quality") val quality: Float,
)

enum class EventTypeEntity {
    SUNRISE,
    SUNSET
}

fun Event.toEventEntity(): EventEntity = EventEntity(
    placeId = placeId,
    timeMillis = time.toEpochMilliseconds(),
    type = type.toEventTypeEntity(),
    quality = quality,
)

fun EventEntity.toEvent(): Event = Event(
    placeId = placeId,
    type = type.toEventType(),
    quality = quality,
    time = Instant.fromEpochMilliseconds(epochMilliseconds = timeMillis),
)

private fun EventType.toEventTypeEntity(): EventTypeEntity = when (this) {
    EventType.SUNRISE -> EventTypeEntity.SUNRISE
    EventType.SUNSET -> EventTypeEntity.SUNSET
}

private fun EventTypeEntity.toEventType(): EventType = when (this) {
    EventTypeEntity.SUNRISE -> EventType.SUNRISE
    EventTypeEntity.SUNSET -> EventType.SUNSET
}
