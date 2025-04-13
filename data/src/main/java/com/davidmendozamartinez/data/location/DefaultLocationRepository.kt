package com.davidmendozamartinez.data.location

import com.davidmendozamartinez.domain.location.Location
import com.davidmendozamartinez.domain.location.LocationRepository
import javax.inject.Inject

class DefaultLocationRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
) : LocationRepository {
    override suspend fun getCurrentLocation(): Location? = locationDataSource.getCurrentLocation()
}
