package com.davidmendozamartinez.database.table.event.datasource

import com.davidmendozamartinez.data.event.EventLocalDataSource
import com.davidmendozamartinez.database.table.event.dao.EventDao
import com.davidmendozamartinez.database.table.event.model.toEvent
import com.davidmendozamartinez.database.table.event.model.toEventEntity
import com.davidmendozamartinez.domain.event.model.Event
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class DefaultEventLocalDataSource @Inject constructor(
    private val eventDao: EventDao,
) : EventLocalDataSource {
    override suspend fun upsertEvents(
        events: List<Event>,
        overwrite: Boolean,
    ) = eventDao.upsertEvents(
        entities = events.map { it.toEventEntity() },
        overwrite = overwrite,
    )

    override fun getUpcomingEventsFlow(placeId: String): Flow<List<Event>> = eventDao
        .getUpcomingEventsFlow(placeId = placeId, start = Clock.System.now().toEpochMilliseconds())
        .distinctUntilChanged()
        .map { entities -> entities.map { it.toEvent() } }
}
