package com.davidmendozamartinez.sunrating.data.place.datasource

import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceLocalDataSource {
    suspend fun getPlaces(): List<Place>

    suspend fun upsertPlace(place: Place)

    suspend fun deletePlace(placeId: String)

    fun getPlacesFlow(): Flow<List<Place>>
}
