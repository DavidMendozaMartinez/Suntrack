package com.davidmendozamartinez.sunrating.location.di

import com.davidmendozamartinez.sunrating.data.location.datasource.LocationDataSource
import com.davidmendozamartinez.sunrating.location.datasource.DefaultLocationDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocationDataSourceModule {
    @Singleton
    @Binds
    fun bindLocationDataSource(impl: DefaultLocationDataSource): LocationDataSource
}
