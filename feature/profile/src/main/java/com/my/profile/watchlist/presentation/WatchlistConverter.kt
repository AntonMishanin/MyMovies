package com.my.profile.watchlist.presentation

import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.profile.watchlist.MoviePreviewItem
import com.my.profile.watchlist.domain.SubscriptionIsInactiveException
import com.xwray.groupie.viewbinding.BindableItem

class WatchlistConverter {

    fun convert(data: List<FavoriteEntity>, onClick: (FavoriteEntity) -> Unit) =
        data.map { MoviePreviewItem(it, onClick) }

    fun progress() = listOf<BindableItem<*>>(ProgressItem())

    fun convert(throwable: Throwable) = when (throwable) {
        is SubscriptionIsInactiveException -> listOf<BindableItem<*>>(AccessErrorItem())
        else -> throw throwable
    }
}