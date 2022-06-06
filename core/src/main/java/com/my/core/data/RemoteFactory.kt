package com.my.core.data

import com.my.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 120_000L

class RemoteFactory {
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.MOVIE_BASE_URL)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(provideRxAdapterFactory())
        .addConverterFactory(provideGsonConverterFactory())
        .build()

    private fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    private fun provideRxAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLogging())
            .addInterceptor(provideInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun provideLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun provideInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val url = chain.request().url
                    .newBuilder()
                    .addQueryParameter("api_key", BuildConfig.MOVIE_API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return chain.proceed(request)
            }
        }
    }
}