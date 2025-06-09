package com.davidmendozamartinez.sunrating.domain.event.model

import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import java.util.UUID
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

    fun setAlarm(settings: EventAlertSettings): Event = copy(
        alarm = if (settings.isEnabled && quality >= settings.qualityThreshold) {
            Alarm.EventAlertAlarm(
                requestCode = Alarm.EventAlertAlarm.generateRequestCode(eventId = id, triggerAt = time - settings.advance),
                triggerAt = time - settings.advance,
                eventId = id,
            )
        } else {
            null
        }
    )

    companion object {
        const val QUALITY_SCALE: Int = 5

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
            quality < 1f -> POOR
            quality < 2f -> FAIR
            quality < 3f -> GOOD
            quality < 4f -> GREAT
            else -> EXCELLENT
        }
    }
}

sealed interface EventOverwritePolicy {
    data object NoOverwrite : EventOverwritePolicy

    data object OverwriteAll : EventOverwritePolicy

    data class OverwriteByType(val type: EventType) : EventOverwritePolicy

    data class OverwriteByPlace(val placeId: String) : EventOverwritePolicy
}
