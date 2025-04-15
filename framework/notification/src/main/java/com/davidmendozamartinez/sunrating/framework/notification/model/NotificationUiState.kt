package com.davidmendozamartinez.sunrating.framework.notification.model

import android.app.PendingIntent
import android.content.Context
import com.davidmendozamartinez.sunrating.domain.notification.model.Notification
import com.davidmendozamartinez.sunrating.framework.notification.R
import com.davidmendozamartinez.sunrating.framework.notification.model.event.EventAlertNotificationUiState
import android.app.Notification as AndroidNotification
import androidx.core.app.NotificationCompat as AndroidNotificationCompat

interface NotificationUiState {
    val id: Int

    fun getContentTitle(context: Context): String

    fun getContentText(context: Context): String
}

fun NotificationUiState.build(
    context: Context,
    channelId: String,
): AndroidNotification = AndroidNotificationCompat.Builder(context, channelId)
    .setSmallIcon(R.drawable.ic_small_icon)
    .setContentTitle(getContentTitle(context))
    .setContentText(getContentText(context))
    .setContentIntent(context.applicationLaunchIntent)
    .setAutoCancel(true)
    .setPriority(AndroidNotificationCompat.PRIORITY_DEFAULT)
    .build()

fun Notification.toNotificationUiState(): NotificationUiState = when (this) {
    is Notification.EventAlertNotification -> EventAlertNotificationUiState(event = event)
}

private val Context.applicationLaunchIntent: PendingIntent?
    get() = packageManager.getLaunchIntentForPackage(packageName)?.let {
        PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }
