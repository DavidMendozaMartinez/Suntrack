package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.domain.event.EventRepository
import com.davidmendozamartinez.domain.event.model.Event
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultEventRepository @Inject constructor(
    private val eventRemoteDataSource: EventRemoteDataSource,
) : EventRepository {
    override suspend fun syncUpcomingEvents(
        latitude: String,
        longitude: String,
    ): Result<Unit> = runCatching {
        val events: List<Event> = eventRemoteDataSource.getUpcomingEvents(
            latitude = latitude,
            longitude = longitude,
        )
    }

    override fun getUpcomingEventsFlow(): Flow<List<Event>> {
        TODO("Not yet implemented")
    }
}
