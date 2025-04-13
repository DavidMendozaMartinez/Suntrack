package com.davidmendozamartinez.data.location

interface LocationDataSource {
    suspend fun getCurrentLocation(): Pair<Double, Double>?
}
