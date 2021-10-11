package com.my.tv_shows.data

import com.my.tv_shows.R
import com.my.tv_shows.entity.TvShowsEntity

class TvShowsDataSource {
    fun fetchTvShows() = listOf(
        TvShowsEntity(id = "", imagePath = R.drawable.preview, title = "Title1", rating = 3f),
        TvShowsEntity(id = "", imagePath = R.drawable.preview, title = "Title2", rating = 3f),
        TvShowsEntity(id = "", imagePath = R.drawable.preview, title = "Title3", rating = 3f),
        TvShowsEntity(id = "", imagePath = R.drawable.preview, title = "Title4", rating = 3f)
    )
}