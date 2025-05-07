package com.davidmendozamartinez.sunrating.framework.database.entity.event.datasource

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventOverwritePolicy
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.framework.database.entity.event.dao.EventDao
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.toEvent
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.toEventAlertAlarm
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.toEventAlertAlarmEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.toEventEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.toEventOverwritePolicyEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.toEventTypeEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class DefaultEventLocalDataSource @Inject constructor(
    private val eventDao: EventDao,
) : EventLocalDataSource {
    override suspend fun getEvent(id: String): Event? = eventDao.getEvent(id = id)?.toEvent()

    override suspend fun getEvents(type: EventType): List<Event> = eventDao
        .getEvents(type = type.toEventTypeEntity())
        .map { it.toEvent() }

    override suspend fun getEventAlertAlarms(): List<Alarm.EventAlertAlarm> = eventDao
        .getEventAlertAlarms()
        .map { it.toEventAlertAlarm() }

    override suspend fun getEventAlertAlarms(eventType: EventType): List<Alarm.EventAlertAlarm> = eventDao
        .getEventAlertAlarms(eventType = eventType.toEventTypeEntity())
        .map { it.toEventAlertAlarm() }

    override suspend fun upsertEvents(
        events: List<Event>,
        overwritePolicy: EventOverwritePolicy,
    ) = eventDao.upsertEvents(
        eventEntities = events.map { it.toEventEntity() },
        alarmEntities = events.mapNotNull { it.alarm?.toEventAlertAlarmEntity() },
        overwritePolicy = overwritePolicy.toEventOverwritePolicyEntity(),
    )

    override fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>> = eventDao
        .getUpcomingEventsFlow(placeId = placeId, start = Clock.System.now().toEpochMilliseconds())
        .distinctUntilChanged()
        .map { entities -> entities.map { it.toEvent() } }
}
