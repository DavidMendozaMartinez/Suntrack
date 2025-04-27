package com.davidmendozamartinez.sunrating.framework.preferences.di

import com.davidmendozamartinez.sunrating.data.place.datasource.PlacePreferencesDataSource
import com.davidmendozamartinez.sunrating.data.settings.datasource.NotificationSettingsPreferencesDataSource
import com.davidmendozamartinez.sunrating.framework.preferences.DefaultPlacePreferencesDataSource
import com.davidmendozamartinez.sunrating.framework.preferences.settings.datasource.DefaultNotificationSettingsPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesDataSourceModule {
    @Singleton
    @Binds
    fun bindPlacePreferencesDataSource(default: DefaultPlacePreferencesDataSource): PlacePreferencesDataSource

    @Singleton
    @Binds
    fun bindNotificationSettingsPreferencesDataSource(
        default: DefaultNotificationSettingsPreferencesDataSource,
    ): NotificationSettingsPreferencesDataSource
}
