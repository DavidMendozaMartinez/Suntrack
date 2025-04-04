package com.davidmendozamartinez.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidmendozamartinez.database.table.event.dao.EventDao
import com.davidmendozamartinez.database.table.event.model.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
