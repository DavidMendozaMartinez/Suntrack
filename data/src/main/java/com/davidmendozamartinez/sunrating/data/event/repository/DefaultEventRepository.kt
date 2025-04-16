package com.davidmendozamartinez.sunrating.data.event.repository

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.data.event.datasource.EventRemoteDataSource
import com.davidmendozamartinez.sunrating.data.place.datasource.PlaceLocalDataSource
import com.davidmendozamartinez.sunrating.domain.event.model.Event
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
) : EventRepository {
    override suspend fun getEvent(id: String): Event? = eventLocalDataSource.getEvent(id = id)

    override suspend fun syncUpcomingEvents(): Result<Unit> = runCatching {
        val places: List<Place> = placeLocalDataSource.getPlaces()
        val events: List<Event> = coroutineScope {
            places.map { async { eventRemoteDataSource.getUpcomingEvents(place = it) } }.awaitAll().flatten()
        }
        eventLocalDataSource.upsertEvents(events = events, overwrite = true)
    }

    override fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>> = eventLocalDataSource
        .getUpcomingEventsFlow(placeId = placeId)
}
