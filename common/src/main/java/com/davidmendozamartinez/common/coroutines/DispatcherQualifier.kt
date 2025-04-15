package com.davidmendozamartinez.common.coroutines

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Dispatcher(val dispatcher: SunRatingDispatchers)

enum class SunRatingDispatchers {
    IO,
}
