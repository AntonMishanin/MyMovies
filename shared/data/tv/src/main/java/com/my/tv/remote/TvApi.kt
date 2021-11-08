package com.my.tv.remote

import com.my.tv.remote.dto.TvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET

internal interface TvApi {
    @GET("tv/popular")
    fun fetchPopularTvShows(): Single<TvShowsResponse>
}