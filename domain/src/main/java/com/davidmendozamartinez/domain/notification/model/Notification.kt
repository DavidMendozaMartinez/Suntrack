package com.davidmendozamartinez.domain.notification.model

import com.davidmendozamartinez.domain.event.model.Event

sealed interface Notification {
    data class EventAlertNotification(val event: Event) : Notification
}
