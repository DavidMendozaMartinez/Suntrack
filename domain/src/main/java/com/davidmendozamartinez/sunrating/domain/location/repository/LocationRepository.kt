package com.davidmendozamartinez.sunrating.domain.location.repository

typealias Location = Pair<Double, Double>

interface LocationRepository {
    suspend fun getCurrentLocation(): Location?
}
