package com.davidmendozamartinez.sunrating.data.location

interface LocationDataSource {
    suspend fun getCurrentLocation(): Pair<Double, Double>?
}
