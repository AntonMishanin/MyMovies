package com.my.tv.remote

import com.my.tv.remote.dto.TvShowsResponse
import io.reactivex.Single

class TvRemoteDataSource(
    private val tvApi: TvApi
) {
    fun fetchPopularTvShows(): Single<TvShowsResponse> = tvApi.fetchPopularTvShows()
}