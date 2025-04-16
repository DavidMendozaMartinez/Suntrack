package com.davidmendozamartinez.sunrating.data.location.repository

import com.davidmendozamartinez.sunrating.data.location.datasource.LocationDataSource
import com.davidmendozamartinez.sunrating.domain.location.repository.Location
import com.davidmendozamartinez.sunrating.domain.location.repository.LocationRepository
import javax.inject.Inject

class DefaultLocationRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
) : LocationRepository {
    override suspend fun getCurrentLocation(): Location? = locationDataSource.getCurrentLocation()
}
