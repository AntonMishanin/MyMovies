package com.my.tv_shows.data.remote

import com.my.tv_shows.data.remote.dto.TvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TvApi {

    @GET("tv/{type}")
    fun fetchTvShows(
        @Path("type") type: String,
        @Query("page") page: Int
    ): Single<TvShowsResponse>
}