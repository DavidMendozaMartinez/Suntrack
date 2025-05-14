package com.davidmendozamartinez.sunrating.domain.event.repository

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    suspend fun getEvent(id: String): Event?

    suspend fun syncEvents(): Result<Unit>

    fun getEventsFlow(placeId: String): Flow<List<Event>>
}
