package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.domain.event.EventRepository
import com.davidmendozamartinez.domain.event.model.Event
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultEventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
    private val eventLocalDataSource: EventLocalDataSource,
) : EventRepository {
    override suspend fun syncUpcomingEvents(
        latitude: String,
        longitude: String,
    ): Result<Unit> = runCatching {
        val events: List<Event> = eventRemoteDataSource.getUpcomingEvents(
            latitude = latitude,
            longitude = longitude,
        )

        eventLocalDataSource.upsertEvents(events = events, overwrite = true)
    }

    override fun getUpcomingEventsFlow(): Flow<List<Event>> = eventLocalDataSource.getUpcomingEventsFlow()
}
