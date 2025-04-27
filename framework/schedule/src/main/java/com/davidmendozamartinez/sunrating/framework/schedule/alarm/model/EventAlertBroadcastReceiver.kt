package com.davidmendozamartinez.sunrating.framework.schedule.alarm.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.davidmendozamartinez.sunrating.common.coroutines.Dispatcher
import com.davidmendozamartinez.sunrating.common.coroutines.SunRatingDispatchers.IO
import com.davidmendozamartinez.sunrating.domain.event.model.Event
import com.davidmendozamartinez.sunrating.domain.event.model.EventType
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.notification.manager.NotificationManager
import com.davidmendozamartinez.sunrating.domain.notification.model.Notification
import com.davidmendozamartinez.sunrating.domain.notification.model.NotificationChannel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventAlertBroadcastReceiver : BroadcastReceiver() {
    @Dispatcher(IO)
    @Inject
    lateinit var dispatcher: CoroutineDispatcher

    @Inject
    lateinit var eventRepository: EventRepository

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        CoroutineScope(context = dispatcher).launch {
            val eventId: String = intent.extras?.getString(EXTRA_EVENT_ID_NAME) ?: return@launch
            val event: Event = eventRepository.getEvent(id = eventId) ?: return@launch

            notificationManager.notify(
                notification = Notification.EventAlertNotification(event = event),
                notificationChannel = when (event.type) {
                    EventType.SUNRISE -> NotificationChannel.SUNRISE_ALERT
                    EventType.SUNSET -> NotificationChannel.SUNSET_ALERT
                },
            )
        }
    }

    companion object {
        const val EXTRA_EVENT_ID_NAME: String = "extra_event_id_name"
    }
}
