package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventLocalDataSource {
    suspend fun getEvent(id: String): Event?

    suspend fun upsertEvents(
        events: List<Event>,
        overwrite: Boolean,
    )

    fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>>
}
