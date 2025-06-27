package com.davidmendozamartinez.sunrating.data.event.repository

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.data.event.datasource.EventRemoteDataSource
import com.davidmendozamartinez.sunrating.data.place.datasource.PlaceLocalDataSource
import com.davidmendozamartinez.sunrating.data.settings.datasource.SettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.alarm.manager.AlarmManager
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventOverwritePolicy
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

class DefaultEventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val settingsPreferencesDataSource: SettingsPreferencesDataSource,
    private val alarmManager: AlarmManager,
) : EventRepository {
    override suspend fun getEvent(id: String): Event? = eventLocalDataSource.getEvent(id = id)

    override suspend fun syncEvents(): Result<Unit> = runCatching {
        val places: List<Place> = placeLocalDataSource.getPlaces()
        val events: List<Event> = coroutineScope {
            places.map { async { eventRemoteDataSource.getEvents(place = it) } }.awaitAll().flatten()
        }
        val eventsWithAlarm: List<Event> = events.setAlarms()

        alarmManager.reset(
            latest = eventsWithAlarm.mapNotNull { it.alarm },
            current = eventLocalDataSource.getEventAlertAlarms(),
        )
        eventLocalDataSource.upsertEvents(
            events = eventsWithAlarm,
            overwritePolicy = EventOverwritePolicy.OverwriteAll,
        )
    }

    override suspend fun syncEvents(placeId: String): Result<Unit> = runCatching {
        val place: Place = placeLocalDataSource.getPlace(id = placeId) ?: throw IllegalArgumentException("Place not found")
        val events: List<Event> = eventRemoteDataSource.getEvents(place = place)
        val eventsWithAlarm: List<Event> = events.setAlarms()

        alarmManager.reset(
            latest = eventsWithAlarm.mapNotNull { it.alarm },
            current = eventLocalDataSource.getEventAlertAlarms(placeId = placeId),
        )
        eventLocalDataSource.upsertEvents(
            events = eventsWithAlarm,
            overwritePolicy = EventOverwritePolicy.OverwriteByPlace(placeId = placeId),
        )
    }

    override fun getEventsFlow(
        placeId: String,
        start: Instant,
        endInclusive: Instant,
    ): Flow<List<Event>> = eventLocalDataSource.getEventsFlow(
        placeId = placeId,
        start = start,
        endInclusive = endInclusive,
    )

    private suspend fun List<Event>.setAlarms(): List<Event> = groupBy { it.type }
        .flatMap { (type: EventType, events: List<Event>) ->
            val settings = settingsPreferencesDataSource.getEventAlertSettings(eventType = type)
            events.map { event -> event.setAlarm(settings = settings) }
        }
}
