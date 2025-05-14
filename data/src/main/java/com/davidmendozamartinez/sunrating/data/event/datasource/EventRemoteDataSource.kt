package com.davidmendozamartinez.sunrating.data.event.datasource

import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.place.model.Place

interface EventRemoteDataSource {
    suspend fun getEvents(place: Place): List<Event>
}
