package com.my.domain.usecase

import com.my.domain.entity.MovieDetails
import com.my.domain.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaveMovieToFavoriteUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(movieDetails: MovieDetails): Completable = repository.insert(movieDetails)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}