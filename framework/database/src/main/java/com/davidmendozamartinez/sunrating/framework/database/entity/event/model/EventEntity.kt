package com.davidmendozamartinez.sunrating.framework.database.entity.event.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.framework.database.entity.place.model.PlaceEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.place.model.toPlace
import kotlinx.datetime.Instant

@Entity(
    tableName = "event",
    foreignKeys = [
        ForeignKey(
            entity = PlaceEntity::class,
            parentColumns = ["id"],
            childColumns = ["place_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class EventEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "place_id") val placeId: String,
    @ColumnInfo(name = "time_millis") val timeMillis: Long,
    @ColumnInfo(name = "type") val type: EventTypeEntity,
    @ColumnInfo(name = "quality") val quality: Float,
)

data class EventWithPlaceRelation(
    @Embedded val event: EventEntity,
    @Relation(
        parentColumn = "place_id",
        entityColumn = "id"
    )
    val place: PlaceEntity
)

enum class EventTypeEntity {
    SUNRISE,
    SUNSET
}

fun Event.toEventEntity(): EventEntity = EventEntity(
    id = id,
    placeId = place.id,
    timeMillis = time.toEpochMilliseconds(),
    type = type.toEventTypeEntity(),
    quality = quality,
)

fun EventWithPlaceRelation.toEvent(): Event = Event(
    id = event.id,
    place = place.toPlace(),
    time = Instant.fromEpochMilliseconds(epochMilliseconds = event.timeMillis),
    type = event.type.toEventType(),
    quality = event.quality,
)

private fun EventType.toEventTypeEntity(): EventTypeEntity = when (this) {
    EventType.SUNRISE -> EventTypeEntity.SUNRISE
    EventType.SUNSET -> EventTypeEntity.SUNSET
}

private fun EventTypeEntity.toEventType(): EventType = when (this) {
    EventTypeEntity.SUNRISE -> EventType.SUNRISE
    EventTypeEntity.SUNSET -> EventType.SUNSET
}
