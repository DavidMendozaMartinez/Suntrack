package com.davidmendozamartinez.sunrating.data.settings.repository

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.data.settings.datasource.SettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.alarm.manager.AlarmManager
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventOverwritePolicy
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import com.davidmendozamartinez.sunrating.domain.settings.repository.SettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultSettingsRepository @Inject constructor(
    private val settingsPreferencesDataSource: SettingsPreferencesDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
    private val alarmManager: AlarmManager,
) : SettingsRepository {
    override suspend fun getEventAlertSettings(eventType: EventType): EventAlertSettings =
        settingsPreferencesDataSource.getEventAlertSettings(eventType = eventType)

    override suspend fun setEventAlertSettings(
        eventType: EventType,
        settings: EventAlertSettings,
    ) {
        settingsPreferencesDataSource.setEventAlertSettings(
            eventType = eventType,
            settings = settings,
        )

        val events: List<Event> = eventLocalDataSource.getEvents(type = eventType)
        val eventsWithAlarm: List<Event> = events.map { event -> event.setAlarm(settings = settings) }

        alarmManager.reset(
            latest = eventsWithAlarm.mapNotNull { it.alarm },
            current = eventLocalDataSource.getEventAlertAlarms(eventType = eventType),
        )
        eventLocalDataSource.upsertEvents(
            events = eventsWithAlarm,
            overwritePolicy = EventOverwritePolicy.OverwriteByType(type = eventType),
        )
    }

    override fun getEventAlertSettingsFlow(eventType: EventType): Flow<EventAlertSettings> =
        settingsPreferencesDataSource.getEventAlertSettingsFlow(eventType = eventType)
}
