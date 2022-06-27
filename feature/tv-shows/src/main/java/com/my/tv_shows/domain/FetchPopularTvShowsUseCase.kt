package com.my.tv_shows.domain

import com.my.core.extensions.applySchedulers

class FetchPopularTvShowsUseCase(
    private val repository: TvRepository
) {

    operator fun invoke() = repository.fetchPopularTvShows()
        .doOnError {
            it.printStackTrace()
        }.applySchedulers()
}