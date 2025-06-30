package com.davidmendozamartinez.sunrating.domain.settings.repository

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun getEventAlertSettings(eventType: EventType): EventAlertSettings

    suspend fun setEventAlertSettings(settings: EventAlertSettings)

    fun getEventAlertSettingsFlow(): Flow<List<EventAlertSettings>>
}
