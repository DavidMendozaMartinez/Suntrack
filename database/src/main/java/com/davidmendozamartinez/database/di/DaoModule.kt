package com.davidmendozamartinez.database.di

import com.davidmendozamartinez.database.AppDatabase
import com.davidmendozamartinez.database.table.event.dao.EventDao
import com.davidmendozamartinez.database.table.place.dao.PlaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Singleton
    @Provides
    fun provideEventDao(database: AppDatabase): EventDao = database.eventDao()

    @Singleton
    @Provides
    fun providePlaceDao(database: AppDatabase): PlaceDao = database.placeDao()
}
