package com.davidmendozamartinez.sunrating.common.coroutines

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dispatcher(val dispatcher: SunRatingDispatchers)

enum class SunRatingDispatchers {
    IO,
}
