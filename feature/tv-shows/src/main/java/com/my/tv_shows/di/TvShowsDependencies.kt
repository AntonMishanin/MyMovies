package com.my.tv_shows.di

import android.content.Context
import com.my.core.di.FeatureDependencies
import com.my.core.di.SchedulersWrapper
import retrofit2.Retrofit

interface TvShowsDependencies : FeatureDependencies {

    fun provideRetrofit(): Retrofit

    fun provideSchedulersWrapper(): SchedulersWrapper

    fun provideContext(): Context
}