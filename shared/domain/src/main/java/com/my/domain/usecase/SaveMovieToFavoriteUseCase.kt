package com.my.domain.usecase

import com.my.domain.entity.MovieDetails
import com.my.domain.repository.FavoriteRepository
import com.my.resources.extensions.applySchedulers
import io.reactivex.Completable

class SaveMovieToFavoriteUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(movieDetails: MovieDetails): Completable =
        repository.insert(movieDetails).applySchedulers()
}