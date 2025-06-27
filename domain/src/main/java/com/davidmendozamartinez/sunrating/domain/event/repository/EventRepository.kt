package com.davidmendozamartinez.sunrating.domain.event.repository

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

interface EventRepository {
    suspend fun getEvent(id: String): Event?

    suspend fun syncEvents(): Result<Unit>

    suspend fun syncEvents(placeId: String): Result<Unit>

    fun getEventsFlow(
        placeId: String,
        start: Instant,
        endInclusive: Instant,
    ): Flow<List<Event>>
}
