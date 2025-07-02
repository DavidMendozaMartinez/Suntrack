package com.davidmendozamartinez.sunrating.data.settings.repository

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.data.settings.datasource.SettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.alarm.manager.AlarmManager
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventOverwritePolicy
import com.davidmendozamartinez.sunrating.domain.settings.model.EventAlertSettings
import com.davidmendozamartinez.sunrating.domain.settings.repository.SettingsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultSettingsRepository @Inject constructor(
    private val settingsPreferencesDataSource: SettingsPreferencesDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
    private val alarmManager: AlarmManager,
) : SettingsRepository {
    override suspend fun setEventAlertSettings(settings: List<EventAlertSettings>) {
        settingsPreferencesDataSource.setEventAlertSettings(settings = settings)

        settings.forEach {
            val events: List<Event> = eventLocalDataSource.getUpcomingEvents(type = it.eventType)
            val eventsWithAlarm: List<Event> = events.map { event -> event.setAlarm(settings = it) }

            alarmManager.reset(
                latest = eventsWithAlarm.mapNotNull { event -> event.alarm },
                current = eventLocalDataSource.getEventAlertAlarms(eventType = it.eventType),
            )
            eventLocalDataSource.upsertEvents(
                events = eventsWithAlarm,
                overwritePolicy = EventOverwritePolicy.OverwriteByType(type = it.eventType),
            )
        }
    }

    override fun getEventAlertSettingsFlow(): Flow<List<EventAlertSettings>> =
        settingsPreferencesDataSource.getEventAlertSettingsFlow()
}
