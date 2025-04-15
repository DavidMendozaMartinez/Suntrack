package com.davidmendozamartinez.sunrating.framework.network.sunsethue.service

import com.davidmendozamartinez.sunrating.framework.network.sunsethue.model.ForecastDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SunsethueService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): ForecastDTO

    companion object {
        const val BASE_URL: String = "https://api.sunsethue.com/"
    }
}
