package com.davidmendozamartinez.sunrating.data.settings.datasource

import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import kotlinx.coroutines.flow.Flow

interface SettingsPreferencesDataSource {
    suspend fun getEventAlertSettings(eventType: EventType): EventAlertSettings

    suspend fun setEventAlertSettings(settings: EventAlertSettings)

    fun getEventAlertSettingsFlow(eventType: EventType): Flow<EventAlertSettings>
}
