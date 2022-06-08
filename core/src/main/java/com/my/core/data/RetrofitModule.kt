package com.my.core.data

import com.my.core.di.BuildConfigWrapper
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

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
        authInterceptor: Interceptor,
        @Named(TIMEOUT_KEY) timeout: Long
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .connectTimeout(timeout, TimeUnit.MILLISECONDS)
        .readTimeout(timeout, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Named(TIMEOUT_KEY)
    internal fun provideTimeout() = 120_000L

    @Provides
    internal fun provideLogging(buildConfigWrapper: BuildConfigWrapper) =
        HttpLoggingInterceptor().apply {
            level = when (buildConfigWrapper.isDebug()) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    internal fun provideInterceptor(buildConfigWrapper: BuildConfigWrapper): Interceptor =
        AuthInterceptor(buildConfigWrapper)

    private companion object {
        const val TIMEOUT_KEY = "Timeout"
    }
}