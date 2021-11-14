package com.my.domain.usecase

import com.my.domain.repository.FavoriteRepository
import com.my.domain.utils.applySchedulers
import io.reactivex.Completable

class DeleteFromFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(id: Int): Completable = repository.deleteById(id).applySchedulers()
}