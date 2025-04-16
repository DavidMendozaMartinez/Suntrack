package com.davidmendozamartinez.sunrating.framework.notification.di

import com.davidmendozamartinez.sunrating.domain.notification.manager.NotificationManager
import com.davidmendozamartinez.sunrating.framework.notification.manager.DefaultNotificationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {
    @Singleton
    @Binds
    fun bindNotificationManager(impl: DefaultNotificationManager): NotificationManager
}
