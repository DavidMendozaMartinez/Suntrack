package com.davidmendozamartinez.sunrating.domain.event.model

import java.util.UUID
import kotlinx.datetime.Instant

data class Event(
    val id: String,
    val placeId: String,
    val time: Instant,
    val type: EventType,
    val quality: Float,
) {
    val qualityCategory: QualityCategory = QualityCategory.from(quality = quality)

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
