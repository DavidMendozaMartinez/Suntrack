package com.davidmendozamartinez.sunrating.domain.alarm.model

sealed interface Alarm {
    val requestCode: Int

    data class EventAlertAlarm(
        override val requestCode: Int,
        val eventId: String,
    ) : Alarm
}
