package com.my.profile.watchlist.di

import com.my.core.di.Feature
import com.my.profile.watchlist.data.PurchaseRepository
import com.my.profile.watchlist.data.WatchlistRepository
import com.my.profile.watchlist.domain.FetchWatchlistUseCase
import com.my.profile.watchlist.presentation.WatchlistConverter
import com.my.profile.watchlist.presentation.WatchlistViewModel
import dagger.Module
import dagger.Provides

@Module
internal class WatchlistModule {

    @Feature
    @Provides
    fun provideWatchlistViewModel(
        fetchWatchlistUseCase: FetchWatchlistUseCase,
        watchlistConverter: WatchlistConverter
    ) = WatchlistViewModel(fetchWatchlistUseCase, watchlistConverter)

    @Provides
    fun provideWatchlistConverter() = WatchlistConverter()

    @Provides
    fun provideWatchlistUseCase(
        purchaseRepository: PurchaseRepository,
        watchlistRepository: WatchlistRepository
    ) = FetchWatchlistUseCase(purchaseRepository, watchlistRepository)

    @Provides
    fun providePurchaseRepository() = PurchaseRepository()

    @Provides
    fun provideWatchlistRepository() = WatchlistRepository()
}