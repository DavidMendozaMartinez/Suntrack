package com.davidmendozamartinez.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidmendozamartinez.framework.database.entity.event.dao.EventDao
import com.davidmendozamartinez.framework.database.entity.event.model.EventEntity
import com.davidmendozamartinez.framework.database.entity.place.dao.PlaceDao
import com.davidmendozamartinez.framework.database.entity.place.model.PlaceEntity

@Database(
    entities = [
        EventEntity::class,
        PlaceEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    abstract fun placeDao(): PlaceDao
}
