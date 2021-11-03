package com.my.movie

import com.my.movie.dto.CreditsResponse
import com.my.movie.dto.MovieDetailsResponse
import com.my.movie.dto.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
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