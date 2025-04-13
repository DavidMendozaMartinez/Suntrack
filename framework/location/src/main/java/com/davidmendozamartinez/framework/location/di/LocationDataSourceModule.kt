package com.davidmendozamartinez.framework.location.di

import com.davidmendozamartinez.data.location.LocationDataSource
import com.davidmendozamartinez.framework.location.datasource.DefaultLocationDataSource
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
