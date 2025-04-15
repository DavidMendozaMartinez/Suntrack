package com.davidmendozamartinez.sunrating.data.location

import com.davidmendozamartinez.sunrating.domain.location.Location
import com.davidmendozamartinez.sunrating.domain.location.LocationRepository
import javax.inject.Inject

class DefaultLocationRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
) : LocationRepository {
    override suspend fun getCurrentLocation(): Location? = locationDataSource.getCurrentLocation()
}
