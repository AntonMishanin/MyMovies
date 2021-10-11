package com.my.movie_details.data

import com.my.movie_details.R
import com.my.movie_details.entity.MovieEntity

class MovieDetailsRepository {

    fun getMovieById(id: String) = MovieEntity(
        id = "",
        previewId = R.drawable.aquaman_preview,
        title = "Movie title",
        rating = 4.3f,
        isFavorite = false,
        descriptionFull = "Deeeeeeeeeeeeeeeeescription full",
        actorsList = fetchActorsByMovie(),
        studio = "Warners",
        genre = "Genre",
        year = "1957"
    )

    private fun fetchActorsByMovie() = listOf(
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        )
    )
}