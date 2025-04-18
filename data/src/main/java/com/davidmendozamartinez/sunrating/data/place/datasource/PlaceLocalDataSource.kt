package com.davidmendozamartinez.sunrating.data.place.datasource

import com.davidmendozamartinez.sunrating.domain.place.model.Place
import kotlinx.coroutines.flow.Flow

interface PlaceLocalDataSource {
    suspend fun getPlaces(): List<Place>

    suspend fun getFirstAvailablePlaceIdSortedByName(excludedIds: List<String>): String?

    suspend fun upsertPlace(place: Place)

    suspend fun deletePlace(id: String)

    fun getPlacesSortedByNameFlow(): Flow<List<Place>>

    fun getPlaceFlow(id: String): Flow<Place?>
}
