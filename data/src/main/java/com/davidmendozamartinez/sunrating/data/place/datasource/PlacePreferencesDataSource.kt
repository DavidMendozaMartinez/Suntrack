package com.davidmendozamartinez.sunrating.data.place.datasource

import kotlinx.coroutines.flow.Flow

interface PlacePreferencesDataSource {
    suspend fun getCurrentPlaceId(): String?

    suspend fun setCurrentPlaceId(placeId: String?)

    fun getCurrentPlaceIdFlow(): Flow<String?>
}
