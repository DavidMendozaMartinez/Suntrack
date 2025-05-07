package com.davidmendozamartinez.sunrating.framework.database.entity.event.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventAlertAlarmEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventOverwritePolicyEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventTypeEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventWithPlaceAndAlarmRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Transaction
    @Query("SELECT * FROM event WHERE place_id = :placeId AND time_millis > :start")
    fun getUpcomingEventsFlow(
        placeId: String,
        start: Long,
    ): Flow<List<EventWithPlaceAndAlarmRelation>>

    @Transaction
    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getEvent(id: String): EventWithPlaceAndAlarmRelation?

    @Transaction
    @Query("SELECT * FROM event WHERE type = :type")
    suspend fun getEvents(type: EventTypeEntity): List<EventWithPlaceAndAlarmRelation>

    @Query("SELECT * FROM event_alert_alarm")
    suspend fun getEventAlertAlarms(): List<EventAlertAlarmEntity>

    @Query(
        """
        SELECT alarm.*
        FROM event_alert_alarm AS alarm
        INNER JOIN event AS event ON event.id = alarm.event_id
        WHERE event.type = :eventType
        """
    )
    suspend fun getEventAlertAlarms(eventType: EventTypeEntity): List<EventAlertAlarmEntity>

    @Upsert
    suspend fun upsertEvents(entities: List<EventEntity>)

    @Upsert
    suspend fun upsertEventAlertAlarms(entities: List<EventAlertAlarmEntity>)

    @Transaction
    suspend fun upsertEvents(
        eventEntities: List<EventEntity>,
        alarmEntities: List<EventAlertAlarmEntity>,
        overwritePolicy: EventOverwritePolicyEntity,
    ) {
        when (overwritePolicy) {
            is EventOverwritePolicyEntity.NoOverwrite -> Unit
            is EventOverwritePolicyEntity.OverwriteAll -> {
                deleteEventAlertAlarms()
                deleteEvents()
            }

            is EventOverwritePolicyEntity.OverwriteByType -> {
                deleteEventAlertAlarms(eventType = overwritePolicy.type)
                deleteEvents(type = overwritePolicy.type)
            }
        }
        upsertEvents(entities = eventEntities)
        upsertEventAlertAlarms(entities = alarmEntities)
    }

    @Query("DELETE FROM event")
    suspend fun deleteEvents()

    @Query("DELETE FROM event WHERE type = :type")
    suspend fun deleteEvents(type: EventTypeEntity)

    @Query("DELETE FROM event_alert_alarm")
    suspend fun deleteEventAlertAlarms()

    @Query(
        """
        DELETE FROM event_alert_alarm
        WHERE event_id IN (
            SELECT event.id
            FROM event
            WHERE event.type = :eventType
        )
        """
    )
    suspend fun deleteEventAlertAlarms(eventType: EventTypeEntity)
}
