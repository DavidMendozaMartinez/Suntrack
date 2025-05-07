package com.davidmendozamartinez.sunrating.framework.schedule.di

import android.app.AlarmManager
import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AndroidModule {
    @Singleton
    @Provides
    fun provideAlarmManager(
        @ApplicationContext context: Context,
    ): AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @Singleton
    @Provides
    fun provideWorkManager(
        @ApplicationContext context: Context,
    ): WorkManager = WorkManager.getInstance(context = context)
}
