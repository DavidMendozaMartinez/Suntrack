package com.davidmendozamartinez.framework.notification.model

import android.content.Context
import androidx.annotation.StringRes
import com.davidmendozamartinez.domain.notification.model.NotificationChannel
import com.davidmendozamartinez.framework.notification.R
import androidx.core.app.NotificationChannelCompat as AndroidNotificationChannel
import androidx.core.app.NotificationManagerCompat as AndroidNotificationManager

enum class NotificationChannelUiState(
    val id: String,
    @StringRes val nameResId: Int,
    @StringRes val descriptionResId: Int,
) {
    SUNRISE_ALERT(
        id = "channel_sunrise_alert",
        nameResId = R.string.notification_channel_sunrise_alert_name,
        descriptionResId = R.string.notification_channel_sunrise_alert_description,
    ),
    SUNSET_ALERT(
        id = "channel_sunset_alert",
        nameResId = R.string.notification_channel_sunset_alert_name,
        descriptionResId = R.string.notification_channel_sunset_alert_description,
    );

    fun build(context: Context): AndroidNotificationChannel =
        AndroidNotificationChannel.Builder(id, AndroidNotificationManager.IMPORTANCE_DEFAULT)
            .setName(context.getString(nameResId))
            .setDescription(context.getString(descriptionResId))
            .build()
}

fun NotificationChannel.toNotificationChannelUiState(): NotificationChannelUiState = when (this) {
    NotificationChannel.SUNRISE_ALERT -> NotificationChannelUiState.SUNRISE_ALERT
    NotificationChannel.SUNSET_ALERT -> NotificationChannelUiState.SUNSET_ALERT
}
