package com.davidmendozamartinez.sunrating.di.data

import com.davidmendozamartinez.sunrating.data.event.repository.DefaultEventRepository
import com.davidmendozamartinez.sunrating.data.location.repository.DefaultLocationRepository
import com.davidmendozamartinez.sunrating.data.place.repository.DefaultPlaceRepository
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.location.repository.LocationRepository
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
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
