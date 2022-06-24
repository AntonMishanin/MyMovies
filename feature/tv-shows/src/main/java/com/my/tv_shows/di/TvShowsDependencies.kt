package com.my.tv_shows.di

import com.my.core.di.FeatureDependencies
import retrofit2.Retrofit

interface TvShowsDependencies : FeatureDependencies {

    fun provideRetrofit(): Retrofit
}