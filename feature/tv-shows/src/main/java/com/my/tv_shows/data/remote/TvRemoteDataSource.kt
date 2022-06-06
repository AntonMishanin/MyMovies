package com.my.tv_shows.data.remote

import com.my.tv_shows.data.remote.dto.TvShowsResponse
import io.reactivex.Single

internal class TvRemoteDataSource(
    private val tvApi: TvApi
) {
    fun fetchPopularTvShows(): Single<TvShowsResponse> = tvApi.fetchPopularTvShows()
}