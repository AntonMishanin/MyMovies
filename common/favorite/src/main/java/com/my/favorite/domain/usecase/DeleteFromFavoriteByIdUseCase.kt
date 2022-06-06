package com.my.favorite.domain.usecase

import com.my.core.extensions.applySchedulers
import com.my.favorite.domain.repository.FavoriteRepository
import io.reactivex.Completable

class DeleteFromFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(id: Int): Completable = repository.deleteById(id).applySchedulers()
}