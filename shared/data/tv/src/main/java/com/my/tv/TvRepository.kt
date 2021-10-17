package com.my.tv

import com.my.tv.remote.TvRemoteDataSource

class TvRepository(
    private val remote: TvRemoteDataSource
) {
    fun fetchPopularTvShows() {
        remote.fetchPopularTvShows()
    }
}