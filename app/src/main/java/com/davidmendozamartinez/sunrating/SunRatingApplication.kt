package com.davidmendozamartinez.sunrating

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.davidmendozamartinez.sunrating.domain.work.manager.WorkManager
import com.davidmendozamartinez.sunrating.domain.work.model.Work
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SunRatingApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        workManager.enqueue(work = Work.Periodic.EventSynchronizer)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory = workerFactory)
            .build()
}
