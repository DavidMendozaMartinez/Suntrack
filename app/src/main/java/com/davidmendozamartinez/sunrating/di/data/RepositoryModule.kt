package com.davidmendozamartinez.sunrating.di.data

import com.davidmendozamartinez.data.event.DefaultEventRepository
import com.davidmendozamartinez.data.location.DefaultLocationRepository
import com.davidmendozamartinez.data.place.DefaultPlaceRepository
import com.davidmendozamartinez.domain.event.EventRepository
import com.davidmendozamartinez.domain.location.LocationRepository
import com.davidmendozamartinez.domain.place.PlaceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindEventRepository(impl: DefaultEventRepository): EventRepository

    @Singleton
    @Binds
    fun bindPlaceRepository(impl: DefaultPlaceRepository): PlaceRepository

    @Singleton
    @Binds
    fun bindLocationRepository(impl: DefaultLocationRepository): LocationRepository
}
