package com.my.tv_shows.data.remote

internal class TvRemoteDataSource(
    private val tvApi: TvApi
) {

    fun fetchTvShows(type: String, page: Int) = tvApi.fetchTvShows(type, page)
}