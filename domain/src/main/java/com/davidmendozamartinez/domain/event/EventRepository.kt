package com.davidmendozamartinez.domain.event

import com.davidmendozamartinez.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun syncUpcomingEvents(): Result<Unit>

    fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>>
}
