package com.my.movies.feed.di

import android.content.Context
import com.my.core.di.FeatureDependencies
import retrofit2.Retrofit

interface FeedDependencies : FeatureDependencies {

    fun provideContext(): Context

    fun provideRetrofit(): Retrofit
}