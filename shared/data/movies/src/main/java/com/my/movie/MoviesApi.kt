package com.my.movie

import com.my.movie.dto.CreditsResponse
import com.my.movie.dto.MovieDetailsResponse
import com.my.movie.dto.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("movie/now_playing")
    fun fetchNowPlaying(): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun fetchUpcoming(): Call<MoviesResponse>

    @GET("movie/popular")
    fun fetchPopular(): Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun fetchMovieById(
        @Path("movie_id") id: String
    ): Call<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    fun fetchCreditsByMovieId(
        @Path("movie_id") id: String
    ): Call<CreditsResponse>
}