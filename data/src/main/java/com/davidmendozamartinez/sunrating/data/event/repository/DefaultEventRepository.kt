package com.davidmendozamartinez.sunrating.data.event.repository

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.data.event.datasource.EventRemoteDataSource
import com.davidmendozamartinez.sunrating.data.place.datasource.PlaceLocalDataSource
import com.davidmendozamartinez.sunrating.data.settings.datasource.SettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.alarm.manager.AlarmManager
import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class DefaultEventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val settingsPreferencesDataSource: SettingsPreferencesDataSource,
    private val alarmManager: AlarmManager,
) : EventRepository {
    override suspend fun getEvent(id: String): Event? = eventLocalDataSource.getEvent(id = id)

    override suspend fun syncUpcomingEvents(): Result<Unit> = runCatching {
        val places: List<Place> = placeLocalDataSource.getPlaces()
        val events: List<Event> = coroutineScope {
            places.map { async { eventRemoteDataSource.getUpcomingEvents(place = it) } }.awaitAll().flatten()
        }

        val eventsWithAlarm: List<Event> = events.setAlarms()
        adjustAlarms(latest = eventsWithAlarm.mapNotNull { it.alarm }, current = eventLocalDataSource.getEventAlertAlarms())

        eventLocalDataSource.upsertEvents(events = eventsWithAlarm, overwrite = true)
    }

    override fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>> = eventLocalDataSource
        .getUpcomingEventsFlow(placeId = placeId)

    private suspend fun List<Event>.setAlarms(): List<Event> = groupBy { it.type }
        .flatMap { (type: EventType, events: List<Event>) ->
            val settings = settingsPreferencesDataSource.getEventAlertNotificationSettings(eventType = type)
            val qualityThreshold: Float = settings.qualityThreshold ?: return events
            events.map { event -> if (event.quality >= qualityThreshold) event.setAlarm(advance = settings.advance) else event }
        }

    private fun adjustAlarms(
        latest: List<Alarm.EventAlertAlarm>,
        current: List<Alarm.EventAlertAlarm>
    ) {
        val alarmsToCancel: List<Alarm.EventAlertAlarm> = current - latest.toSet()
        val alarmsToSchedule: List<Alarm.EventAlertAlarm> = latest - current.toSet()

        alarmsToSchedule.forEach { alarmManager.schedule(alarm = it) }
        alarmsToCancel.forEach { alarmManager.cancel(alarm = it) }
    }
}
