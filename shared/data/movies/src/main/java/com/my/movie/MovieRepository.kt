package com.my.movie

class MovieRepository(
    private val moviesDataSource: MoviesDataSource
) {
    fun fetchNowPlaying(onSuccess: () -> Unit) {
        moviesDataSource.fetchNowPlaying {

        }
    }

    fun fetchUpcoming(onSuccess: () -> Unit) {
        moviesDataSource.fetchUpcoming {

        }
    }

    fun fetchPopular(onSuccess: () -> Unit) {
        moviesDataSource.fetchPopular {

        }
    }

    fun fetchMovieById(id: String) {
        moviesDataSource.fetchMovieById(id)
    }

    fun fetchCreditsByMovieId(id: String) {
        moviesDataSource.fetchCreditsByMovieId(id)
    }
}