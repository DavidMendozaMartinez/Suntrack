package com.davidmendozamartinez.sunrating.framework.notification.manager

import android.content.Context
import com.davidmendozamartinez.sunrating.domain.notification.manager.NotificationManager
import com.davidmendozamartinez.sunrating.domain.notification.model.Notification
import com.davidmendozamartinez.sunrating.domain.notification.model.NotificationChannel
import com.davidmendozamartinez.sunrating.framework.notification.model.NotificationChannelUiState
import com.davidmendozamartinez.sunrating.framework.notification.model.NotificationUiState
import com.davidmendozamartinez.sunrating.framework.notification.model.build
import com.davidmendozamartinez.sunrating.framework.notification.model.toNotificationChannelUiState
import com.davidmendozamartinez.sunrating.framework.notification.model.toNotificationUiState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.app.NotificationChannelCompat as AndroidNotificationChannel
import androidx.core.app.NotificationManagerCompat as AndroidNotificationManager

class DefaultNotificationManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val androidNotificationManager: AndroidNotificationManager
) : NotificationManager {

    init {
        val channels: List<AndroidNotificationChannel> = NotificationChannel.entries.map { channel ->
            channel.toNotificationChannelUiState().build(context = context)
        }
        androidNotificationManager.createNotificationChannelsCompat(channels)
    }

    override fun notify(
        notification: Notification,
        notificationChannel: NotificationChannel,
    ) {
        val notificationUiState: NotificationUiState = notification.toNotificationUiState()
        val notificationChannelUiState: NotificationChannelUiState = notificationChannel.toNotificationChannelUiState()

        androidNotificationManager.notify(
            notificationUiState.id,
            notificationUiState.build(context = context, channelId = notificationChannelUiState.id),
        )
    }

    override fun areNotificationsEnabled(): Boolean = androidNotificationManager.areNotificationsEnabled()
}
