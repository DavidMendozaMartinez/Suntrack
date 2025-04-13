package com.davidmendozamartinez.framework.database.di

import com.davidmendozamartinez.framework.database.AppDatabase
import com.davidmendozamartinez.framework.database.table.event.dao.EventDao
import com.davidmendozamartinez.framework.database.table.place.dao.PlaceDao
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
