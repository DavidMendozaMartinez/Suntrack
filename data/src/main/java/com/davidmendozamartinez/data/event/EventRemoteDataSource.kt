package com.davidmendozamartinez.data.event

import com.davidmendozamartinez.domain.event.model.Event
import com.davidmendozamartinez.domain.place.model.Place

interface EventRemoteDataSource {
    suspend fun getUpcomingEvents(place: Place): List<Event>
}
