package com.my.tv_shows.domain

import com.my.core.domain.Response
import com.my.core.extensions.applySchedulers

class FetchPopularTvShowsUseCase(
    private val repository: TvRepository
) {

    operator fun invoke() = repository.fetchPopularTvShows()
        .doOnSuccess {
            if (it is Response.Error) {
                it.sourceException.printStackTrace()
            }
        }.applySchedulers()
}