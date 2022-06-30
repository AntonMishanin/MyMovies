package com.my.tv_shows.domain

import io.reactivex.Flowable
import io.reactivex.Single

internal interface TvRepository {

    fun observeTvShows(): Flowable<List<TvShowsEntity>>

    fun fetchFreshTvShows(paginationConfig: PaginationConfig): Single<List<TvShowsEntity>>

    fun fetchCachedTvShows(): Single<List<TvShowsEntity>>

    fun saveToCachedTvShows(tvShows: List<TvShowsEntity>)
}