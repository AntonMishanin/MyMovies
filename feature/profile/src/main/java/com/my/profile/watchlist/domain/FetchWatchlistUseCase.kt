package com.my.profile.watchlist.domain

import com.my.profile.watchlist.data.PurchaseRepository
import com.my.profile.watchlist.data.WatchlistRepository

internal class FetchWatchlistUseCase(
    private val purchaseRepository: PurchaseRepository,
    private val watchlistRepository: WatchlistRepository
) {

    fun invoke() = purchaseRepository.isSubscriptionActive()
        .flatMap { subscriptionActive ->
            if (subscriptionActive) {
                watchlistRepository.fetchWatchlist()
            } else {
                throw SubscriptionIsInactiveException()
            }
        }
}