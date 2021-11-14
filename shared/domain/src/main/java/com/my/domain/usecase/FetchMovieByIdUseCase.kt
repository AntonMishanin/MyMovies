package com.my.domain.usecase

import com.my.domain.repository.MovieRepository
import com.my.domain.utils.applySchedulers

class FetchMovieByIdUseCase(private val repository: MovieRepository) {
    operator fun invoke(id: String) = repository.fetchMovieByIdFromNetwork(id).applySchedulers()
}