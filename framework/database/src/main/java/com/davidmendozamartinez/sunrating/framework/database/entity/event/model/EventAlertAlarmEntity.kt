package com.davidmendozamartinez.sunrating.framework.database.entity.event.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import kotlinx.datetime.Instant

@Entity(
    tableName = "event_alert_alarm",
    foreignKeys = [
        ForeignKey(
            entity = EventEntity::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class EventAlertAlarmEntity(
    @PrimaryKey @ColumnInfo(name = "request_code") val requestCode: Int,
    @ColumnInfo(name = "trigger_at_millis") val triggerAtMillis: Long,
    @ColumnInfo(name = "event_id") val eventId: String,
)

fun Alarm.EventAlertAlarm.toEventAlertAlarmEntity(): EventAlertAlarmEntity = EventAlertAlarmEntity(
    requestCode = requestCode,
    triggerAtMillis = triggerAt.toEpochMilliseconds(),
    eventId = eventId,
)

fun EventAlertAlarmEntity.toEventAlertAlarm(): Alarm.EventAlertAlarm = Alarm.EventAlertAlarm(
    requestCode = requestCode,
    triggerAt = Instant.fromEpochMilliseconds(epochMilliseconds = triggerAtMillis),
    eventId = eventId,
)
