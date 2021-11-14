package com.my.movie

import com.my.movie.dto.CreditsResponse
import com.my.movie.dto.MovieDetailsResponse
import com.my.movie.dto.MoviesResponse
import io.reactivex.Single

internal class MoviesDataSource(
    private val moviesApi: MoviesApi
) {
    fun fetchNowPlaying(): Single<MoviesResponse> = moviesApi.fetchNowPlaying()

    fun fetchUpcoming(): Single<MoviesResponse> = moviesApi.fetchUpcoming()

    fun fetchPopular(): Single<MoviesResponse> = moviesApi.fetchPopular()

    fun fetchMovieById(id: String): Single<MovieDetailsResponse> = moviesApi.fetchMovieById(id)

    fun fetchCreditsByMovieId(id: String): Single<CreditsResponse> =
        moviesApi.fetchCreditsByMovieId(id)
}