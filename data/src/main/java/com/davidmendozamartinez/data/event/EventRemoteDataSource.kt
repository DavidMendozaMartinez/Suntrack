package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.domain.event.model.Event

interface EventRemoteDataSource {
    suspend fun getUpcomingEvents(
        latitude: String,
        longitude: String,
    ): List<Event>
}
