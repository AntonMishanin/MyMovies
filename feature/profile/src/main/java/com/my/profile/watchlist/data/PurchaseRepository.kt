package com.my.profile.watchlist.data

import io.reactivex.Single

class PurchaseRepository {

    fun isSubscriptionActive() = Single.just(false)
}