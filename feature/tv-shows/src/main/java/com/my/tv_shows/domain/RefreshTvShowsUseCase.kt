package com.my.tv_shows.domain

import com.my.core.extensions.applySchedulers

internal class RefreshTvShowsUseCase(
    private val repository: TvRepository
) {

    operator fun invoke() = repository.fetchFreshTvShows()
        .doOnError {
            it.printStackTrace()
        }.applySchedulers()
}