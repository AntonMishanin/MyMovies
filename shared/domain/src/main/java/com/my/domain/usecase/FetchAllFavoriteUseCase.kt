package com.my.domain.usecase

import com.my.domain.entity.MovieDetails
import com.my.domain.repository.FavoriteRepository
import com.my.domain.utils.applySchedulers
import io.reactivex.Flowable

class FetchAllFavoriteUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(): Flowable<List<MovieDetails>> = repository.loadAll().applySchedulers()
}