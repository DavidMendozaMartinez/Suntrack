package com.davidmendozamartinez.sunrating.data.event

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.place.model.Place

interface EventRemoteDataSource {
    suspend fun getUpcomingEvents(place: Place): List<Event>
}
