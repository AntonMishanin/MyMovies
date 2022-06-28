package com.my.profile.watchlist.data

import io.reactivex.Single

internal class PurchaseRepository {

    fun isSubscriptionActive() = Single.just(false)
}