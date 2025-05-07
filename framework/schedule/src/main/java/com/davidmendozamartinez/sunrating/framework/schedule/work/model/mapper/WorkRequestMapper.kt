package com.davidmendozamartinez.sunrating.framework.schedule.work.model.mapper

import androidx.work.PeriodicWorkRequest
import com.davidmendozamartinez.sunrating.domain.work.model.Work
import com.davidmendozamartinez.sunrating.framework.schedule.work.model.EventSynchronizerWorker

fun Work.Periodic.toPeriodicWorkRequest(): PeriodicWorkRequest = when (this) {
    Work.Periodic.EventSynchronizer -> EventSynchronizerWorker.request
}
