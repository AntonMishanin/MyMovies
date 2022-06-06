package com.my.movies.data

import com.my.movies.data.dto.CreditsResponse
import com.my.movies.data.dto.MovieDetailsResponse
import com.my.movies.data.dto.MoviesResponse
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