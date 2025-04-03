package com.davidmendozamartinez.network.di

import com.davidmendozamartinez.data.event.EventRemoteDataSource
import com.davidmendozamartinez.network.sunsethue.datasource.DefaultEventRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Singleton
    @Binds
    fun bindEventRemoteDataSource(impl: DefaultEventRemoteDataSource): EventRemoteDataSource
}
