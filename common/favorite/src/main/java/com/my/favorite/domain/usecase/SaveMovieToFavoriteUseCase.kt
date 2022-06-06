package com.my.favorite.domain.usecase

import com.my.core.extensions.applySchedulers
import com.my.favorite.domain.repository.FavoriteRepository
import io.reactivex.Completable

class SaveMovieToFavoriteUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(movieDetails: FavoriteEntity): Completable =
        repository.insert(movieDetails).applySchedulers()
}