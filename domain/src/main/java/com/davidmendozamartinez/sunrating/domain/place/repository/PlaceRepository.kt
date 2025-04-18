package com.davidmendozamartinez.sunrating.domain.place.repository

import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    suspend fun createPlace(
        name: String,
        latitude: Double,
        longitude: Double,
    ): Result<Unit>

    suspend fun setCurrentPlace(id: String): Result<Unit>

    suspend fun deletePlace(id: String): Result<Unit>

    fun getPlacesSortedByNameFlow(): Flow<List<Place>>

    fun getCurrentPlaceFlow(): Flow<Place?>
}
