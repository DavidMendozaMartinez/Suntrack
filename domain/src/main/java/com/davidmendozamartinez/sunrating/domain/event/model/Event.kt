package com.davidmendozamartinez.sunrating.domain.event.model

import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import java.util.UUID
import kotlin.time.Duration
import kotlinx.datetime.Instant

data class Event(
    val id: String,
    val place: Place,
    val time: Instant,
    val type: EventType,
    val quality: Float,
    val alarm: Alarm.EventAlertAlarm?,
) {
    val qualityCategory: QualityCategory = QualityCategory.from(quality = quality)

    fun setAlarm(advance: Duration): Event = copy(
        alarm = Alarm.EventAlertAlarm(
            requestCode = Alarm.EventAlertAlarm.generateRequestCode(eventId = id, triggerAt = time - advance),
            triggerAt = time - advance,
            eventId = id,
        )
    )

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

enum class QualityCategory {
    POOR,
    FAIR,
    GOOD,
    GREAT,
    EXCELLENT;

    companion object {
        fun from(quality: Float): QualityCategory = when {
            quality < 0.2f -> POOR
            quality < 0.4f -> FAIR
            quality < 0.6f -> GOOD
            quality < 0.8f -> GREAT
            else -> EXCELLENT
        }
    }
}
