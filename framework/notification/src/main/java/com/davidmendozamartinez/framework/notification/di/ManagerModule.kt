package com.davidmendozamartinez.framework.notification.di

import com.davidmendozamartinez.domain.notification.NotificationManager
import com.davidmendozamartinez.framework.notification.manager.DefaultNotificationManager
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
