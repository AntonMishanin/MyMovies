package com.my.tv

import com.my.tv.remote.TvRemoteDataSource
import com.my.tv.remote.dto.TvShowsResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TvRepository(
    private val remote: TvRemoteDataSource
) {
    fun fetchPopularTvShows(): Single<TvShowsResponse> {
        return remote.fetchPopularTvShows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}