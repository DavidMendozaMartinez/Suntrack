package com.davidmendozamartinez.sunrating.data.place.repository

import com.davidmendozamartinez.sunrating.data.place.datasource.PlaceLocalDataSource
import com.davidmendozamartinez.sunrating.data.place.datasource.PlacePreferencesDataSource
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class DefaultPlaceRepository @Inject constructor(
    private val placeLocalDataSource: PlaceLocalDataSource,
    private val placePreferencesDataSource: PlacePreferencesDataSource,
) : PlaceRepository {
    override suspend fun createPlace(
        name: String,
        latitude: Double,
        longitude: Double,
    ): Result<Unit> = runCatching {
        val place = Place(
            id = Place.generateUUID(latitude = latitude, longitude = longitude),
            name = name,
            latitude = latitude,
            longitude = longitude,
        )

        placeLocalDataSource.upsertPlace(place = place)

        if (placePreferencesDataSource.getCurrentPlaceId() == null) {
            placePreferencesDataSource.setCurrentPlaceId(placeId = place.id)
        }
    }

    override suspend fun setCurrentPlace(id: String): Result<Unit> = runCatching {
        placePreferencesDataSource.setCurrentPlaceId(placeId = id)
    }

    override suspend fun deletePlace(id: String) = runCatching {
        if (placePreferencesDataSource.getCurrentPlaceId() == id) {
            val newCurrentPlaceId: String? = placeLocalDataSource.getFirstAvailablePlaceIdSortedByName(excludedIds = listOf(id))
            placePreferencesDataSource.setCurrentPlaceId(placeId = newCurrentPlaceId)
        }

        placeLocalDataSource.deletePlace(id = id)
    }

    override fun getPlacesSortedByNameFlow(): Flow<List<Place>> = placeLocalDataSource.getPlacesSortedByNameFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCurrentPlaceFlow(): Flow<Place?> = placePreferencesDataSource
        .getCurrentPlaceIdFlow()
        .flatMapLatest { id ->
            if (id == null) return@flatMapLatest flowOf(value = null)
            placeLocalDataSource.getPlaceFlow(id = id)
        }
}
