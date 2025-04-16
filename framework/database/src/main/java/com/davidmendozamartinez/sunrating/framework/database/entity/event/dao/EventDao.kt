package com.davidmendozamartinez.sunrating.framework.database.entity.event.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventEntity
import com.davidmendozamartinez.sunrating.framework.database.entity.event.model.EventWithPlaceRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Transaction
    @Query("SELECT * FROM event WHERE place_id = :placeId AND time_millis > :start")
    fun getUpcomingEventsFlow(
        placeId: String,
        start: Long,
    ): Flow<List<EventWithPlaceRelation>>

    @Transaction
    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getEvent(id: String): EventWithPlaceRelation?

    @Upsert
    suspend fun upsertEvents(entities: List<EventEntity>)

    @Transaction
    suspend fun upsertEvents(
        entities: List<EventEntity>,
        overwrite: Boolean,
    ) {
        if (overwrite) deleteEvents()
        upsertEvents(entities = entities)
    }

    @Query("DELETE FROM event")
    suspend fun deleteEvents()
}
