package com.davidmendozamartinez.sunrating.data.event.datasource

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventLocalDataSource {
    suspend fun getEvent(id: String): Event?

    suspend fun upsertEvents(
        events: List<Event>,
        overwrite: Boolean,
    )

    fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>>
}
