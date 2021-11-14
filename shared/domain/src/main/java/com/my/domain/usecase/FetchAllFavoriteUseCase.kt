package com.my.domain.usecase

import com.my.domain.entity.MovieDetails
import com.my.domain.repository.FavoriteRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FetchAllFavoriteUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(): Flowable<List<MovieDetails>> = repository.loadAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}