package com.my.tv_shows.data.remote

import com.my.tv_shows.domain.PaginationConfig

internal class TvRemoteDataSource(
    private val tvApi: TvApi
) {

    fun fetchTvShows(
        type: String,
        paginationConfig: PaginationConfig
    ) = tvApi.fetchTvShows(type, paginationConfig.page)
}