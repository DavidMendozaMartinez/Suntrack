package com.davidmendozamartinez.sunrating.location.datasource

import android.location.Location
import com.davidmendozamartinez.sunrating.data.location.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class DefaultLocationDataSource @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationDataSource {
    override suspend fun getCurrentLocation(): Pair<Double, Double>? {
        val location: Location? = fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            CancellationTokenSource().token,
        ).await()
        return location?.let { it.latitude to it.longitude }
    }
}
