package com.my.domain.usecase

import com.my.domain.repository.FavoriteRepository
import com.my.resources.extensions.applySchedulers
import io.reactivex.Completable

class DeleteFromFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(id: Int): Completable = repository.deleteById(id).applySchedulers()
}