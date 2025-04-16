package com.davidmendozamartinez.sunrating.framework.notification.model.event

import android.content.Context
import com.davidmendozamartinez.sunrating.common.extension.format
import com.davidmendozamartinez.sunrating.common.extension.toLocalTime
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.event.model.QualityCategory
import com.davidmendozamartinez.sunrating.framework.notification.R
import com.davidmendozamartinez.sunrating.framework.notification.model.NotificationUiState

data class EventAlertNotificationUiState(val event: Event) : NotificationUiState {
    override val id: Int = event.time.hashCode() // TODO: [future]

    override fun getContentTitle(context: Context): String {
        val resId: Int = when (event.type) {
            EventType.SUNRISE -> when (event.qualityCategory) {
                QualityCategory.GREAT, QualityCategory.EXCELLENT -> R.string.notification_sunrise_quality_content_title
                else -> R.string.notification_sunrise_default_content_title
            }

            EventType.SUNSET -> when (event.qualityCategory) {
                QualityCategory.GREAT, QualityCategory.EXCELLENT -> R.string.notification_sunset_quality_content_title
                else -> R.string.notification_sunset_default_content_title
            }
        }
        return context.getString(resId, event.time.toLocalTime().format(), event.placeId)
    }

    override fun getContentText(context: Context): String {
        val resId: Int = when (event.type) {
            EventType.SUNRISE -> when (event.qualityCategory) {
                QualityCategory.GREAT, QualityCategory.EXCELLENT -> R.string.notification_sunrise_quality_content_text
                else -> R.string.notification_sunrise_default_content_text
            }

            EventType.SUNSET -> when (event.qualityCategory) {
                QualityCategory.GREAT, QualityCategory.EXCELLENT -> R.string.notification_sunset_quality_content_text
                else -> R.string.notification_sunset_default_content_text
            }
        }
        return context.getString(resId, event.time.toLocalTime().format(), event.placeId)
    }
}
