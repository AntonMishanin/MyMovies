package com.my.movies.domain

import com.my.core.extensions.applySchedulers

class FetchMovieByIdUseCase(private val repository: MovieRepository) {
    operator fun invoke(id: String) = repository.fetchMovieByIdFromNetwork(id).applySchedulers()
}