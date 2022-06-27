package com.my.tv_shows.domain

import io.reactivex.Single

internal interface TvRepository {
    fun fetchPopularTvShows(): Single<List<TvShowsEntity>>
}