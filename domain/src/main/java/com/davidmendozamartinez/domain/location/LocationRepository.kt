package com.davidmendozamartinez.domain.location

typealias Location = Pair<Double, Double>

interface LocationRepository {
    suspend fun getCurrentLocation(): Location?
}
