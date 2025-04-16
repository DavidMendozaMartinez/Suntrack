package com.davidmendozamartinez.sunrating.framework.network.di

import com.davidmendozamartinez.sunrating.data.event.datasource.EventRemoteDataSource
import com.davidmendozamartinez.sunrating.framework.network.sunsethue.datasource.DefaultEventRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Singleton
    @Binds
    fun bindEventRemoteDataSource(impl: DefaultEventRemoteDataSource): EventRemoteDataSource
}
