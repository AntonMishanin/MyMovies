package com.my.favorite.domain.usecase

import com.my.core.extensions.applySchedulers
import com.my.favorite.domain.repository.FavoriteRepository

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