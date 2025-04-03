package com.davidmendozamartinez.network.sunsethue.service

import com.davidmendozamartinez.network.sunsethue.model.ForecastDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SunsethueService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
    ): ForecastDTO

    companion object {
        const val BASE_URL: String = "https://api.sunsethue.com/"
    }
}
