package com.my.domain.usecase

import com.my.domain.entity.TvShowsEntity
import com.my.domain.repository.TvRepository
import com.my.resources.extensions.applySchedulers
import io.reactivex.Single

class FetchPopularTvShowsUseCase(private val repository: TvRepository) {
    operator fun invoke(): Single<List<TvShowsEntity>> =
        repository.fetchPopularTvShows().applySchedulers()
}