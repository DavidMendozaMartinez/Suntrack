package com.davidmendozamartinez.sunrating.framework.schedule.work.model

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class EventSynchronizerWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val eventRepository: EventRepository,
) : CoroutineWorker(
    appContext = context,
    params = workerParams,
) {
    override suspend fun doWork(): Result = eventRepository.syncEvents().fold(
        onSuccess = { Result.success() },
        onFailure = { Result.retry() },
    )

    companion object {
        private val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(networkType = NetworkType.CONNECTED)
            .build()

        val request: PeriodicWorkRequest = PeriodicWorkRequestBuilder<EventSynchronizerWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.DAYS,
        )
            .setConstraints(constraints = constraints)
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                backoffDelay = 2,
                timeUnit = TimeUnit.HOURS
            )
            .build()
    }
}
