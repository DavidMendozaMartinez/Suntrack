package com.davidmendozamartinez.sunrating.domain.work.manager

import com.davidmendozamartinez.sunrating.domain.work.model.Work

interface WorkManager {
    fun enqueue(work: Work)
}
