package com.davidmendozamartinez.sunrating.framework.database.di

import com.davidmendozamartinez.sunrating.data.event.datasource.EventLocalDataSource
import com.davidmendozamartinez.sunrating.data.place.datasource.PlaceLocalDataSource
import com.davidmendozamartinez.sunrating.framework.database.entity.event.datasource.DefaultEventLocalDataSource
import com.davidmendozamartinez.sunrating.framework.database.entity.place.datasource.DefaultPlaceLocalDataSource
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
