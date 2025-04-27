package com.davidmendozamartinez.sunrating.domain.alarm.manager

import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm

interface AlarmManager {
    fun schedule(
        alarm: Alarm,
        triggerAtMillis: Long,
    )

    fun cancel(alarm: Alarm)
}
