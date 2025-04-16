package com.davidmendozamartinez.sunrating.data.location.datasource

interface LocationDataSource {
    suspend fun getCurrentLocation(): Pair<Double, Double>?
}
