package com.my.movies.feed.di

import android.content.Context
import com.my.core.di.FeatureDependencies

interface FeedDependencies : FeatureDependencies {

    fun provideContext(): Context
}