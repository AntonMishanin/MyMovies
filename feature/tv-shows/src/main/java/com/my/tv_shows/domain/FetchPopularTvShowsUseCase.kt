package com.my.tv_shows.domain

import com.my.core.extensions.applySchedulers
import io.reactivex.Single

class FetchPopularTvShowsUseCase(private val repository: TvRepository) {
    operator fun invoke(): Single<List<TvShowsEntity>> =
        repository.fetchPopularTvShows().applySchedulers()
}