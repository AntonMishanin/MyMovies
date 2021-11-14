package com.my.domain.usecase

import com.my.domain.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FetchMovieByIdUseCase(private val repository: MovieRepository) {
    operator fun invoke(id: String) = repository.fetchMovieByIdFromNetwork(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}