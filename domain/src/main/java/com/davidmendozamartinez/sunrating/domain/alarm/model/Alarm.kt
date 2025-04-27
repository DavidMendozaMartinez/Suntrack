package com.davidmendozamartinez.sunrating.domain.alarm.model

import kotlinx.datetime.Instant

sealed interface Alarm {
    val requestCode: Int
    val triggerAt: Instant

    data class EventAlertAlarm(
        override val requestCode: Int,
        override val triggerAt: Instant,
        val eventId: String,
    ) : Alarm
}
