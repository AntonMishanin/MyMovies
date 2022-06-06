package com.my.tv_shows.data.remote

import com.my.tv_shows.data.remote.dto.TvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET

internal interface TvApi {
    @GET("tv/popular")
    fun fetchPopularTvShows(): Single<TvShowsResponse>
}