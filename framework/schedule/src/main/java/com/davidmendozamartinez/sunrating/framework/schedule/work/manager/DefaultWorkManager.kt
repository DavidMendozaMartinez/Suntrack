package com.davidmendozamartinez.sunrating.framework.schedule.work.manager

import androidx.work.ExistingPeriodicWorkPolicy
import com.davidmendozamartinez.sunrating.domain.work.manager.WorkManager
import com.davidmendozamartinez.sunrating.domain.work.model.Work
import com.davidmendozamartinez.sunrating.framework.schedule.work.model.mapper.toPeriodicWorkRequest
import javax.inject.Inject
import androidx.work.WorkManager as AndroidWorkManager

class DefaultWorkManager @Inject constructor(
    private val androidWorkManager: AndroidWorkManager,
) : WorkManager {
    override fun enqueue(work: Work) {
        when (work) {
            is Work.Periodic -> androidWorkManager.enqueueUniquePeriodicWork(
                uniqueWorkName = work.uniqueName,
                existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
                request = work.toPeriodicWorkRequest(),
            )
        }
    }
}
