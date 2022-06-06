package com.my.favorite.domain.usecase

import com.my.core.extensions.applySchedulers
import com.my.favorite.domain.repository.FavoriteRepository
import io.reactivex.Flowable

class FetchAllFavoriteUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(): Flowable<List<FavoriteEntity>> = repository.loadAll().applySchedulers()
}