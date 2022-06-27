package com.my.tv_shows.domain

import io.reactivex.Single

interface TvRepository {
    fun fetchPopularTvShows(): Single<List<TvShowsEntity>>
}