package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.domain.event.model.Event
import kotlinx.coroutines.flow.Flow

interface EventLocalDataSource {
    suspend fun upsertEvents(
        events: List<Event>,
        overwrite: Boolean,
    )

    fun getUpcomingEventsFlow(): Flow<List<Event>>
}
