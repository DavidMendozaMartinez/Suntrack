package com.davidmendozamartinez.sunrating.framework.schedule.di

import com.davidmendozamartinez.sunrating.domain.alarm.manager.AlarmManager
import com.davidmendozamartinez.sunrating.domain.work.manager.WorkManager
import com.davidmendozamartinez.sunrating.framework.schedule.alarm.manager.DefaultAlarmManager
import com.davidmendozamartinez.sunrating.framework.schedule.work.manager.DefaultWorkManager
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
    fun bindAlarmManager(default: DefaultAlarmManager): AlarmManager

    @Singleton
    @Binds
    fun bindWorkManager(default: DefaultWorkManager): WorkManager
}
