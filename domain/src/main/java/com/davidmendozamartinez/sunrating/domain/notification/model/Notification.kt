package com.davidmendozamartinez.sunrating.domain.notification.model

import com.davidmendozamartinez.sunrating.domain.event.model.Event

sealed interface Notification {
    data class EventAlertNotification(val event: Event) : Notification
}
