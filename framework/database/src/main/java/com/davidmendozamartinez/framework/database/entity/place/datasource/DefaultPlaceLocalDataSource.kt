package com.davidmendozamartinez.framework.database.entity.place.datasource

import com.davidmendozamartinez.data.place.PlaceLocalDataSource
import com.davidmendozamartinez.domain.place.model.Place
import com.davidmendozamartinez.framework.database.entity.place.dao.PlaceDao
import com.davidmendozamartinez.framework.database.entity.place.model.toPlace
import com.davidmendozamartinez.framework.database.entity.place.model.toPlaceEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class DefaultPlaceLocalDataSource @Inject constructor(
    private val placeDao: PlaceDao,
) : PlaceLocalDataSource {
    override suspend fun getPlaces(): List<Place> = placeDao.getPlaces().map { it.toPlace() }

    override suspend fun upsertPlace(place: Place) = placeDao.upsertPlace(entity = place.toPlaceEntity())

    override suspend fun deletePlace(placeId: String) = placeDao.deletePlace(placeId = placeId)

    override fun getPlacesFlow(): Flow<List<Place>> = placeDao
        .getPlacesFlow()
        .distinctUntilChanged()
        .map { entities -> entities.map { it.toPlace() } }
}
