package com.davidmendozamartinez.sunrating.di.data

import com.davidmendozamartinez.data.event.DefaultEventRepository
import com.davidmendozamartinez.domain.event.EventRepository
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
}
