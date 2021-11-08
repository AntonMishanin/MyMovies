package com.my.domain.repository

import com.my.domain.entity.TvShowsEntity
import io.reactivex.Single

interface TvRepository {
    fun fetchPopularTvShows(): Single<List<TvShowsEntity>>
}