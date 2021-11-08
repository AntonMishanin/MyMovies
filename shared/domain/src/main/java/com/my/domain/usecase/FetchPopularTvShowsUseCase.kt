package com.my.domain.usecase

import com.my.domain.entity.TvShowsEntity
import com.my.domain.repository.TvRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FetchPopularTvShowsUseCase(private val repository: TvRepository) {
    operator fun invoke(): Single<List<TvShowsEntity>> = repository.fetchPopularTvShows()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}