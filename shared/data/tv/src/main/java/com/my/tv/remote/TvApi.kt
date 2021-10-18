package com.my.tv.remote

import com.my.tv.remote.dto.TvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TvApi {
    @GET("tv/popular")
    fun fetchPopularTvShows(): Single<TvShowsResponse>
}