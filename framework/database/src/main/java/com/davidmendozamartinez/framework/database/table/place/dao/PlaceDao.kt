package com.davidmendozamartinez.framework.database.table.place.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.davidmendozamartinez.framework.database.table.place.model.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place")
    fun getPlacesFlow(): Flow<List<PlaceEntity>>

    @Query("SELECT * FROM place")
    suspend fun getPlaces(): List<PlaceEntity>

    @Upsert
    suspend fun upsertPlace(entity: PlaceEntity)

    @Query("DELETE FROM place WHERE id = :placeId")
    suspend fun deletePlace(placeId: String)
}
