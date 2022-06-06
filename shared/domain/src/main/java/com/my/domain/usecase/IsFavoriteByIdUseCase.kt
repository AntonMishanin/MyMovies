package com.my.domain.usecase

import com.my.domain.repository.FavoriteRepository
import com.my.resources.extensions.applySchedulers

class IsFavoriteByIdUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(id: Int, isFavorite: (Boolean) -> Unit) =
        repository.isFavoriteById(id)
            .applySchedulers()
            .subscribe({
                isFavorite(true)
            }, {
                isFavorite(false)
            })
}