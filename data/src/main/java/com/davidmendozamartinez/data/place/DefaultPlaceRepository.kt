package com.davidmendozamartinez.data.place

import com.davidmendozamartinez.domain.place.PlaceRepository
import com.davidmendozamartinez.domain.place.model.Place
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class DefaultPlaceRepository @Inject constructor(
    private val placeLocalDataSource: PlaceLocalDataSource,
) : PlaceRepository {
    override suspend fun createPlace(
        name: String,
        latitude: Double,
        longitude: Double,
    ): Result<Unit> = runCatching {
        placeLocalDataSource.upsertPlace(
            place = Place(
                id = Place.generateUUID(latitude = latitude, longitude = longitude),
                name = name,
                latitude = latitude,
                longitude = longitude,
            )
        )
    }

    override suspend fun deletePlace(placeId: String) = placeLocalDataSource.deletePlace(placeId = placeId)

    override fun getPlacesFlow(): Flow<List<Place>> = placeLocalDataSource.getPlacesFlow()
}
