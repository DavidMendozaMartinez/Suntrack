package com.davidmendozamartinez.domain.event

import com.davidmendozamartinez.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun syncUpcomingEvents(
        latitude: String,
        longitude: String,
    ): Result<Unit>

    fun getUpcomingEventsFlow(): Flow<List<Event>>
}
