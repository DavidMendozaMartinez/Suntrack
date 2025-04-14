package com.davidmendozamartinez.framework.notification.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.core.app.NotificationManagerCompat as AndroidNotificationManager

@Module
@InstallIn(SingletonComponent::class)
class AndroidNotificationModule {
    @Singleton
    @Provides
    fun provideAndroidNotificationManager(
        @ApplicationContext context: Context,
    ): AndroidNotificationManager = AndroidNotificationManager.from(context)
}
