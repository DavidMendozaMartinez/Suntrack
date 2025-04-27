package com.davidmendozamartinez.sunrating.data.settings.repository

import com.davidmendozamartinez.sunrating.data.settings.datasource.NotificationSettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertNotificationSettings
import com.davidmendozamartinez.sunrating.domain.settings.repository.NotificationSettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultNotificationSettingsRepository @Inject constructor(
    private val notificationSettingsPreferencesDataSource: NotificationSettingsPreferencesDataSource,
) : NotificationSettingsRepository {
    override suspend fun getEventAlertNotificationSettings(eventType: EventType): EventAlertNotificationSettings =
        notificationSettingsPreferencesDataSource.getEventAlertNotificationSettings(eventType = eventType)

    override suspend fun setEventAlertNotificationSettings(
        eventType: EventType,
        settings: EventAlertNotificationSettings,
    ) {
        notificationSettingsPreferencesDataSource.setEventAlertNotificationSettings(
            eventType = eventType,
            settings = settings,
        )
    }

    override fun getEventAlertNotificationSettingsFlow(eventType: EventType): Flow<EventAlertNotificationSettings> =
        notificationSettingsPreferencesDataSource.getEventAlertNotificationSettingsFlow(eventType = eventType)
}
