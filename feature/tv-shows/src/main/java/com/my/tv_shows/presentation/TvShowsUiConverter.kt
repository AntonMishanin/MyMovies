package com.my.tv_shows.presentation

import com.my.core.domain.NoInternetConnectionException
import com.my.core.domain.UnknownException
import com.my.tv_shows.domain.TvShowsEntity
import com.my.tv_shows.ui.*

internal class TvShowsUiConverter {

    fun convert(
        input: List<TvShowsEntity>,
        onItemClickedCallback: OnItemClickedCallback
    ) = input.map { TvShowsItem(it, onItemClickedCallback) }

    fun progress() = MutableList(20) { ProgressItem() }

    fun convert(
        exception: Throwable,
        tryAgainClickedCallback: OnTryAgainClickedCallback
    ) = when (exception) {
        is NoInternetConnectionException -> listOf(NoInternetErrorItem(tryAgainClickedCallback))
        is UnknownException -> listOf(UnknownErrorItem(tryAgainClickedCallback))
        else -> throw IllegalArgumentException("Unknown exception $exception")
    }
}