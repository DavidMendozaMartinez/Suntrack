package com.davidmendozamartinez.data.place

import com.davidmendozamartinez.domain.place.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceLocalDataSource {
    suspend fun upsertPlace(place: Place)

    suspend fun deletePlace(placeId: String)

    fun getPlacesFlow(): Flow<List<Place>>
}
