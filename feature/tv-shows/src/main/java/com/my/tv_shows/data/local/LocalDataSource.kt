package com.my.tv_shows.data.local

import io.reactivex.Single

internal class LocalDataSource(
    private val dao: TvShowsDao
) {

    fun insert(list: List<TvShowsDbo>) = dao.insert(list)

    fun fetch(): Single<List<TvShowsDbo>> = dao.fetch()
}