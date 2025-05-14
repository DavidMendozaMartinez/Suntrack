package com.davidmendozamartinez.sunrating.data.event.datasource

import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventOverwritePolicy
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import kotlinx.coroutines.flow.Flow

interface EventLocalDataSource {
    suspend fun getEvent(id: String): Event?

    suspend fun getEvents(type: EventType): List<Event>

    suspend fun getEventAlertAlarms(): List<Alarm.EventAlertAlarm>

    suspend fun getEventAlertAlarms(eventType: EventType): List<Alarm.EventAlertAlarm>

    suspend fun getEventAlertAlarms(placeId: String): List<Alarm.EventAlertAlarm>

    suspend fun upsertEvents(
        events: List<Event>,
        overwritePolicy: EventOverwritePolicy,
    )

    fun getEventsFlow(placeId: String): Flow<List<Event>>
}
