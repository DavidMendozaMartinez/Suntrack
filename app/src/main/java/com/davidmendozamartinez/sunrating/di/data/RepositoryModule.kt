package com.davidmendozamartinez.sunrating.di.data

import com.davidmendozamartinez.sunrating.data.event.repository.DefaultEventRepository
import com.davidmendozamartinez.sunrating.data.location.repository.DefaultLocationRepository
import com.davidmendozamartinez.sunrating.data.place.repository.DefaultPlaceRepository
import com.davidmendozamartinez.sunrating.data.settings.repository.DefaultNotificationSettingsRepository
import com.davidmendozamartinez.sunrating.domain.event.repository.EventRepository
import com.davidmendozamartinez.sunrating.domain.location.repository.LocationRepository
import com.davidmendozamartinez.sunrating.domain.place.repository.PlaceRepository
import com.davidmendozamartinez.sunrating.domain.settings.repository.NotificationSettingsRepository
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
    fun bindEventRepository(default: DefaultEventRepository): EventRepository

    @Singleton
    @Binds
    fun bindPlaceRepository(default: DefaultPlaceRepository): PlaceRepository

    @Singleton
    @Binds
    fun bindLocationRepository(default: DefaultLocationRepository): LocationRepository

    @Singleton
    @Binds
    fun bindNotificationSettingsRepository(default: DefaultNotificationSettingsRepository): NotificationSettingsRepository
}
