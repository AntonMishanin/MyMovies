package com.my.movies.data

import com.my.movies.data.dto.CreditsResponse
import com.my.movies.data.dto.MovieDetailsResponse
import com.my.movies.data.dto.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface MoviesApi {
    @GET("movie/now_playing")
    fun fetchNowPlaying(): Single<MoviesResponse>

    @GET("movie/upcoming")
    fun fetchUpcoming(): Single<MoviesResponse>

    @GET("movie/popular")
    fun fetchPopular(): Single<MoviesResponse>

    @GET("movie/{movie_id}")
    fun fetchMovieById(
        @Path("movie_id") id: String
    ): Single<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    fun fetchCreditsByMovieId(
        @Path("movie_id") id: String
    ): Single<CreditsResponse>
}