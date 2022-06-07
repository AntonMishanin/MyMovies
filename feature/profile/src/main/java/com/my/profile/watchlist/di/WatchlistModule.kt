package com.my.profile.watchlist.di

import com.my.core.di.Feature
import com.my.profile.watchlist.WatchlistViewModel
import dagger.Module
import dagger.Provides

@Module
internal class WatchlistModule {

    @Feature
    @Provides
    fun provideWatchlistViewModel() = WatchlistViewModel()
}