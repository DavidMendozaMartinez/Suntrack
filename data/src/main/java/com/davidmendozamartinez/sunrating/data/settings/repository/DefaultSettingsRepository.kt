package com.davidmendozamartinez.sunrating.data.settings.repository

import com.davidmendozamartinez.sunrating.data.settings.datasource.SettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertNotificationSettings
import com.davidmendozamartinez.sunrating.domain.settings.repository.SettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultSettingsRepository @Inject constructor(
    private val settingsPreferencesDataSource: SettingsPreferencesDataSource,
) : SettingsRepository {
    override suspend fun getEventAlertNotificationSettings(eventType: EventType): EventAlertNotificationSettings =
        settingsPreferencesDataSource.getEventAlertNotificationSettings(eventType = eventType)

    override suspend fun setEventAlertNotificationSettings(
        eventType: EventType,
        settings: EventAlertNotificationSettings,
    ) {
        settingsPreferencesDataSource.setEventAlertNotificationSettings(
            eventType = eventType,
            settings = settings,
        )
    }

    override fun getEventAlertNotificationSettingsFlow(eventType: EventType): Flow<EventAlertNotificationSettings> =
        settingsPreferencesDataSource.getEventAlertNotificationSettingsFlow(eventType = eventType)
}
