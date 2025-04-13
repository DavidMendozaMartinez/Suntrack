package com.davidmendozamartinez.framework.database.di

import com.davidmendozamartinez.data.event.EventLocalDataSource
import com.davidmendozamartinez.data.place.PlaceLocalDataSource
import com.davidmendozamartinez.framework.database.table.event.datasource.DefaultEventLocalDataSource
import com.davidmendozamartinez.framework.database.table.place.datasource.DefaultPlaceLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
    @Singleton
    @Binds
    fun bindEventLocalDataSource(impl: DefaultEventLocalDataSource): EventLocalDataSource

    @Singleton
    @Binds
    fun bindPlaceLocalDataSource(impl: DefaultPlaceLocalDataSource): PlaceLocalDataSource
}
