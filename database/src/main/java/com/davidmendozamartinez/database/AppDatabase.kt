package com.davidmendozamartinez.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidmendozamartinez.database.table.event.dao.EventDao
import com.davidmendozamartinez.database.table.event.model.EventEntity
import com.davidmendozamartinez.database.table.place.dao.PlaceDao
import com.davidmendozamartinez.database.table.place.model.PlaceEntity

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
