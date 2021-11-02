package com.my.movie

import android.util.Log
import com.my.movie.dto.CreditsResponse
import com.my.movie.dto.MovieDetailsResponse
import com.my.movie.dto.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDataSource(
    private val moviesApi: MoviesApi
) {
    fun fetchNowPlaying(onSuccess: (MoviesResponse) -> Unit) {
        val request = moviesApi.fetchNowPlaying()
        request.enqueue(requestCallback(onSuccess))
    }

    fun fetchUpcoming(onSuccess: (MoviesResponse) -> Unit) {
        val request = moviesApi.fetchUpcoming()
        request.enqueue(requestCallback(onSuccess))
    }

    fun fetchPopular(onSuccess: (MoviesResponse) -> Unit) {
        val request = moviesApi.fetchPopular()
        request.enqueue(requestCallback(onSuccess))
    }

    private fun requestCallback(onSuccess: (MoviesResponse) -> Unit) =
        object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.body() != null) {
                    onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                t.printStackTrace()
            }
        }

    fun fetchMovieById(id: String) {
        val request = moviesApi.fetchMovieById(id)
        request.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                Log.d("EE", "fetchMovieById = ${response.body()}")
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun fetchCreditsByMovieId(id: String) {
        val request = moviesApi.fetchCreditsByMovieId(id)
        request.enqueue(object : Callback<CreditsResponse> {
            override fun onResponse(
                call: Call<CreditsResponse>,
                response: Response<CreditsResponse>
            ) {
                Log.d("EE", "fetchCreditsByMovieId = ${response.body()}")
            }

            override fun onFailure(call: Call<CreditsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}