package com.davidmendozamartinez.sunrating.domain.settings.repository

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertNotificationSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getEventAlertNotificationSettings(eventType: EventType): EventAlertNotificationSettings

    suspend fun setEventAlertNotificationSettings(
        eventType: EventType,
        settings: EventAlertNotificationSettings,
    )

    fun getEventAlertNotificationSettingsFlow(eventType: EventType): Flow<EventAlertNotificationSettings>
}
