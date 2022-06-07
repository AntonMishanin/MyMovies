package com.my.core.data

import com.my.core.di.BuildConfigWrapper
import dagger.Module
import dagger.Provides
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

@Module
class RetrofitModule {

    @Provides
    fun provideRetrofit(
        buildConfigWrapper: BuildConfigWrapper,
        okHttpClient: OkHttpClient,
        rxAdapterFactory: CallAdapter.Factory,
        gsonConverterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(buildConfigWrapper.movieBaseUrl())
        .client(okHttpClient)
        .addCallAdapterFactory(rxAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    internal fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    internal fun provideRxAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    internal fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    @Provides
    internal fun provideLogging(buildConfigWrapper: BuildConfigWrapper): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (buildConfigWrapper.isDebug()) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    @Provides
    internal fun provideInterceptor(buildConfigWrapper: BuildConfigWrapper): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val url = chain.request().url
                    .newBuilder()
                    .addQueryParameter("api_key", buildConfigWrapper.movieApiKey())
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return chain.proceed(request)
            }
        }
    }

    companion object {
        private const val TIMEOUT = 120_000L
    }
}