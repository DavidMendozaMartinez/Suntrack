package com.davidmendozamartinez.network.di

import com.davidmendozamartinez.network.BuildConfig
import com.davidmendozamartinez.network.sunsethue.interceptor.SunsethueHeaderInterceptor
import com.davidmendozamartinez.network.sunsethue.service.SunsethueService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SunsethueRetrofit

@Module
@InstallIn(SingletonComponent::class)
class SunsethueModule {
    @Singleton
    @Provides
    @SunsethueRetrofit
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor = HttpLoggingInterceptor().apply { if (BuildConfig.DEBUG) level = Level.BODY })
            .addInterceptor(interceptor = SunsethueHeaderInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(SunsethueService.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
            .client(client)
            .build()
    }
}
