package com.davidmendozamartinez.sunrating.framework.schedule.alarm.manager

import android.content.Context
import com.davidmendozamartinez.sunrating.domain.alarm.manager.AlarmManager
import com.davidmendozamartinez.sunrating.domain.alarm.model.Alarm
import com.davidmendozamartinez.sunrating.framework.schedule.alarm.model.mapper.toPendingIntent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import android.app.AlarmManager as AndroidAlarmManager
import androidx.core.app.AlarmManagerCompat as AndroidAlarmManagerCompat

class DefaultAlarmManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val androidAlarmManager: AndroidAlarmManager,
) : AlarmManager {
    override fun schedule(
        alarm: Alarm,
        triggerAtMillis: Long,
    ) {
        if (AndroidAlarmManagerCompat.canScheduleExactAlarms(androidAlarmManager)) {
            androidAlarmManager.setExact(
                AndroidAlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                alarm.toPendingIntent(context = context),
            )
        } else {
            androidAlarmManager.set(
                AndroidAlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                alarm.toPendingIntent(context = context),
            )
        }
    }

    override fun cancel(alarm: Alarm) {
        androidAlarmManager.cancel(alarm.toPendingIntent(context = context))
    }
}
