package com.my.core.data

import com.my.core.di.BuildConfigWrapper
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(
    private val buildConfigWrapper: BuildConfigWrapper
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter(API_KEY_NAME, buildConfigWrapper.movieApiKey())
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

    private companion object {
        const val API_KEY_NAME = "api_key"
    }
}