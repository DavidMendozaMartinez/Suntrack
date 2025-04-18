package com.davidmendozamartinez.sunrating.framework.database.entity.place.datasource

import com.davidmendozamartinez.sunrating.data.place.datasource.PlaceLocalDataSource
import com.davidmendozamartinez.sunrating.domain.place.model.Place
import com.davidmendozamartinez.sunrating.framework.database.entity.place.dao.PlaceDao
import com.davidmendozamartinez.sunrating.framework.database.entity.place.model.toPlace
import com.davidmendozamartinez.sunrating.framework.database.entity.place.model.toPlaceEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class DefaultPlaceLocalDataSource @Inject constructor(
    private val placeDao: PlaceDao,
) : PlaceLocalDataSource {
    override suspend fun getPlaces(): List<Place> = placeDao.getPlaces().map { it.toPlace() }

    override suspend fun getFirstAvailablePlaceIdSortedByName(excludedIds: List<String>): String? =
        placeDao.getFirstAvailablePlaceIdSortedByName(excludedIds = excludedIds)

    override suspend fun upsertPlace(place: Place) = placeDao.upsertPlace(entity = place.toPlaceEntity())

    override suspend fun deletePlace(id: String) = placeDao.deletePlace(id = id)

    override fun getPlacesSortedByNameFlow(): Flow<List<Place>> = placeDao
        .getPlacesSortedByNameFlow()
        .distinctUntilChanged()
        .map { entities -> entities.map { it.toPlace() } }

    override fun getPlaceFlow(id: String): Flow<Place?> = placeDao
        .getPlaceFlow(id = id)
        .distinctUntilChanged()
        .map { it?.toPlace() }
}
