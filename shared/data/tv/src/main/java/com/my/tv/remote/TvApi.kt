package com.my.tv.remote

import com.my.tv.remote.dto.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET

interface TvApi {
    @GET("tv/popular")
    fun fetchPopularTvShows(): Call<TvShowsResponse>
}