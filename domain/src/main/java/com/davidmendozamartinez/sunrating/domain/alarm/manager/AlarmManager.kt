package com.davidmendozamartinez.sunrating.domain.alarm.manager

import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm

interface AlarmManager {
    fun schedule(alarm: Alarm)

    fun cancel(alarm: Alarm)

    fun reset(
        latest: List<Alarm>,
        current: List<Alarm>,
    )

    fun canScheduleExactAlarms(): Boolean
}
