package com.davidmendozamartinez.domain.notification

import com.davidmendozamartinez.domain.notification.model.Notification
import com.davidmendozamartinez.domain.notification.model.NotificationChannel

interface NotificationManager {
    fun notify(
        notification: Notification,
        notificationChannel: NotificationChannel,
    )
}
