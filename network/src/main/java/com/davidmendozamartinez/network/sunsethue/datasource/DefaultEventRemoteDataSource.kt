package com.davidmendozamartinez.network.sunsethue.datasource

import com.davidmendozamartinez.data.event.EventRemoteDataSource
import com.davidmendozamartinez.domain.event.model.Event
import com.davidmendozamartinez.network.sunsethue.model.ForecastDTO
import com.davidmendozamartinez.network.sunsethue.model.toEvent
import com.davidmendozamartinez.network.sunsethue.service.SunsethueService
import javax.inject.Inject

class DefaultEventRemoteDataSource @Inject constructor(
    private val sunsethueService: SunsethueService,
) : EventRemoteDataSource {
    override suspend fun getUpcomingEvents(
        latitude: String,
        longitude: String,
    ): List<Event> {
        val forecast: ForecastDTO = sunsethueService.getForecast(
            latitude = latitude,
            longitude = longitude,
        )
        return forecast.data.mapNotNull { it.toEvent() }
    }
}
