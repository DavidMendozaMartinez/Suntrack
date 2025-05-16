package com.davidmendozamartinez.sunrating.domain.settings.repository

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getEventAlertSettings(eventType: EventType): EventAlertSettings

    suspend fun setEventAlertSettings(
        eventType: EventType,
        settings: EventAlertSettings,
    )

    fun getEventAlertSettingsFlow(eventType: EventType): Flow<EventAlertSettings>
}
