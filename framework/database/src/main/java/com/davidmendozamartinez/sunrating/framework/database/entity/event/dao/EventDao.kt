package com.davidmendozamartinez.sunrating.framework.database.entity.event.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventAlertAlarmEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventEntity
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

    @Query("SELECT * FROM event_alert_alarm")
    suspend fun getEventAlertAlarms(): List<EventAlertAlarmEntity>

    @Upsert
    suspend fun upsertEvents(entities: List<EventEntity>)

    @Upsert
    suspend fun upsertEventAlertAlarms(entities: List<EventAlertAlarmEntity>)

    @Transaction
    suspend fun upsertEvents(
        eventEntities: List<EventEntity>,
        alarmEntities: List<EventAlertAlarmEntity>,
        overwrite: Boolean,
    ) {
        if (overwrite) {
            deleteEventAlertAlarms()
            deleteEvents()
        }
        upsertEvents(entities = eventEntities)
        upsertEventAlertAlarms(entities = alarmEntities)
    }

    @Query("DELETE FROM event")
    suspend fun deleteEvents()

    @Query("DELETE FROM event_alert_alarm")
    suspend fun deleteEventAlertAlarms()
}
