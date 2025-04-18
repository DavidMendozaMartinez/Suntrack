package com.davidmendozamartinez.sunrating.framework.database.entity.place.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.davidmendozamartinez.sunrating.framework.database.entity.place.model.PlaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM place ORDER BY name")
    fun getPlacesSortedByNameFlow(): Flow<List<PlaceEntity>>

    @Query("SELECT * FROM place WHERE id = :id")
    fun getPlaceFlow(id: String): Flow<PlaceEntity?>

    @Query("SELECT * FROM place")
    suspend fun getPlaces(): List<PlaceEntity>

    @Query("SELECT id FROM place WHERE id NOT IN (:excludedIds) ORDER BY name LIMIT 1")
    suspend fun getFirstAvailablePlaceIdSortedByName(excludedIds: List<String>): String?

    @Upsert
    suspend fun upsertPlace(entity: PlaceEntity)

    @Query("DELETE FROM place WHERE id = :id")
    suspend fun deletePlace(id: String)
}
