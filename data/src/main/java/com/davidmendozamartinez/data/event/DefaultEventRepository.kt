package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.data.place.PlaceLocalDataSource
import com.davidmendozamartinez.domain.event.EventRepository
import com.davidmendozamartinez.domain.event.model.Event
import com.davidmendozamartinez.domain.place.model.Place
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
