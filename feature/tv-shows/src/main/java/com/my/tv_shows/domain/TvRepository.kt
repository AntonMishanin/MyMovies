package com.my.tv_shows.domain

import com.my.core.domain.Response
import io.reactivex.Single

interface TvRepository {
    fun fetchPopularTvShows(): Single<Response<List<TvShowsEntity>>>
}