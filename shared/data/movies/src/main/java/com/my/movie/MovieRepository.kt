package com.my.movie

import com.my.domain.entity.Movie

class MovieRepository(
    private val moviesDataSource: MoviesDataSource
) {
    fun fetchNowPlaying(onSuccess: (List<Movie>?) -> Unit) {
        moviesDataSource.fetchNowPlaying {
            onSuccess(it.toValueObject())
        }
    }

    fun fetchUpcoming(onSuccess: (List<Movie>?) -> Unit) {
        moviesDataSource.fetchUpcoming {
            onSuccess(it.toValueObject())
        }
    }

    fun fetchPopular(onSuccess: (List<Movie>?) -> Unit) {
        moviesDataSource.fetchPopular {
            onSuccess(it.toValueObject())
        }
    }

    fun fetchMovieById(id: String) {
        moviesDataSource.fetchMovieById(id)
    }

    fun fetchCreditsByMovieId(id: String) {
        moviesDataSource.fetchCreditsByMovieId(id)
    }
}