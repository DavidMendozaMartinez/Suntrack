package com.davidmendozamartinez.domain.place

import com.davidmendozamartinez.domain.place.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceRepository {
    suspend fun createPlace(
        name: String,
        latitude: Double,
        longitude: Double,
    ): Result<Unit>

    suspend fun deletePlace(placeId: String)

    fun getPlacesFlow(): Flow<List<Place>>
}
