package com.my.tv_shows.data

class TvShowsRepository(private val dataSource: TvShowsDataSource) {
    fun fetchTvShows() = dataSource.fetchTvShows()
}