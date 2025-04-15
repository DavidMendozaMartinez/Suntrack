package com.davidmendozamartinez.sunrating.di.common

import com.davidmendozamartinez.sunrating.common.coroutines.Dispatcher
import com.davidmendozamartinez.sunrating.common.coroutines.SunRatingDispatchers.IO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {
    @Dispatcher(IO)
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}
