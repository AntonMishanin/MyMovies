package com.my.tv_shows.presentation

import com.my.core.domain.NoInternetConnectionException
import com.my.core.domain.UnknownException
import com.my.tv_shows.domain.TvShowsEntity
import com.my.tv_shows.ui.*

internal class TvShowsUiConverter {

    fun convert(
        input: List<TvShowsEntity>,
        toggleOverviewCallback: ToggleOverviewCallback
    ) = input.map { TvShowsItem(it, toggleOverviewCallback) }

    fun progress() = MutableList(20) { ProgressItem() }

    fun convert(
        exception: Throwable,
        refreshCallback: RefreshCallback
    ) = when (exception) {
        is NoInternetConnectionException -> listOf(NoInternetErrorItem(refreshCallback))
        is UnknownException -> listOf(UnknownErrorItem(refreshCallback))
        else -> throw IllegalArgumentException("Unknown exception $exception")
    }
}