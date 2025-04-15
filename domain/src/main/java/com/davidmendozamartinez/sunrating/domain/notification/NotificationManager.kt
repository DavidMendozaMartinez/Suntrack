package com.davidmendozamartinez.sunrating.domain.notification

import com.davidmendozamartinez.sunrating.domain.notification.model.Notification
import com.davidmendozamartinez.sunrating.domain.notification.model.NotificationChannel

interface NotificationManager {
    fun notify(
        notification: Notification,
        notificationChannel: NotificationChannel,
    )
}
