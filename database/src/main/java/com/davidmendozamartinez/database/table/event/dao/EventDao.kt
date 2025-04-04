package com.davidmendozamartinez.database.table.event.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.davidmendozamartinez.database.table.event.model.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM event WHERE time_millis > :start")
    fun getUpcomingEventsFlow(start: Long): Flow<List<EventEntity>>

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
