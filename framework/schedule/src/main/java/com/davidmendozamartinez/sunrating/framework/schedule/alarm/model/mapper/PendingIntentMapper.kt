package com.davidmendozamartinez.sunrating.framework.schedule.alarm.model.mapper

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.framework.schedule.alarm.model.EventAlertBroadcastReceiver

fun Alarm.toPendingIntent(context: Context): PendingIntent {
    val (clazz: Class<out BroadcastReceiver>, extras: Bundle) = when (this) {
        is Alarm.EventAlertAlarm -> Pair(
            first = EventAlertBroadcastReceiver::class.java,
            second = bundleOf(EventAlertBroadcastReceiver.EXTRA_EVENT_ID_NAME to eventId),
        )
    }

    val intent = Intent(context, clazz)
    intent.putExtras(extras)
    return PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_IMMUTABLE)
}
