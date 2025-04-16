package com.davidmendozamartinez.sunrating.domain.event.repository

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvent(id: String): Event?

    suspend fun syncUpcomingEvents(): Result<Unit>

    fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>>
}
