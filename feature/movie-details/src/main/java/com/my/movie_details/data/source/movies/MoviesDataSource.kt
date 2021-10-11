package com.my.movie_details.data.source.movies

import com.my.movie_details.R

class MoviesDataSource {
    fun fetchMovieById(id: String) = MovieDto(
        id = "",
        previewId = R.drawable.aquaman_preview,
        title = "Movie title",
        rating = 4.3f,
        isFavorite = false,
        descriptionFull = "Deeeeeeeeeeeeeeeeescription full",
        actorsIdList = listOf("id1", "id2", "id3"),
        studiosIdList = listOf("id1", "id2", "id3"),
        genresIdList = listOf("id1", "id2", "id3"),
        year = 1957
    )
}