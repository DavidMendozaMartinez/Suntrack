package com.davidmendozamartinez.network.di

import com.davidmendozamartinez.network.sunsethue.service.SunsethueService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Singleton
    @Provides
    fun provideSunsethueService(
        @SunsethueRetrofit retrofit: Retrofit,
    ): SunsethueService = retrofit.create(SunsethueService::class.java)
}
