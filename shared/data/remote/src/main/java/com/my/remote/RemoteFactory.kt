package com.my.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteFactory {
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.MOVIE_BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(provideGsonConverterFactory())
        .build()

    private fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLogging())
            .addInterceptor(provideInterceptor())
            .connectTimeout(120_000, TimeUnit.MILLISECONDS)
            .readTimeout(120_000, TimeUnit.MILLISECONDS)
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