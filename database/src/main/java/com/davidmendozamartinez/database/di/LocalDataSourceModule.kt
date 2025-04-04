package com.davidmendozamartinez.database.di

import com.davidmendozamartinez.data.event.EventLocalDataSource
import com.davidmendozamartinez.database.table.event.datasource.DefaultEventLocalDataSource
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
}
