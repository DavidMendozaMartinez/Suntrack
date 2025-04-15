package com.davidmendozamartinez.sunrating.framework.network.sunsethue.interceptor

import com.davidmendozamartinez.sunrating.framework.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

object SunsethueHeaderInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val request: Request = chain.request().newBuilder()
            .header(name = "x-api-key", value = BuildConfig.SUNSETHUE_API_KEY)
            .build()

        return chain.proceed(request = request)
    }
}
