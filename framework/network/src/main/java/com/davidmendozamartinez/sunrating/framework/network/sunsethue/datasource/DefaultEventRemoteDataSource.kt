package com.davidmendozamartinez.sunrating.framework.network.sunsethue.datasource

import com.davidmendozamartinez.sunrating.data.event.datasource.EventRemoteDataSource
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import com.davidmendozamartinez.sunrating.framework.network.sunsethue.model.ForecastDTO
import com.davidmendozamartinez.sunrating.framework.network.sunsethue.model.toEvent
import com.davidmendozamartinez.sunrating.framework.network.sunsethue.service.SunsethueService
import javax.inject.Inject

class DefaultEventRemoteDataSource @Inject constructor(
    private val sunsethueService: SunsethueService,
) : EventRemoteDataSource {
    override suspend fun getUpcomingEvents(place: Place): List<Event> {
        val forecast: ForecastDTO = sunsethueService.getForecast(
            latitude = place.latitude,
            longitude = place.longitude,
        )
        return forecast.data.mapNotNull { it.toEvent(place = place) }
    }
}
