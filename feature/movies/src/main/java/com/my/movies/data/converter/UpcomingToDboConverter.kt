package com.my.movies.data.converter

import com.my.core.converter.IterableConverter
import com.my.movies.data.storage.dto.UpcomingEntity
import com.my.movies.domain.Movie

internal class UpcomingToDboConverter : IterableConverter<Movie, UpcomingEntity>() {

    override fun convert(input: Movie) = UpcomingEntity(
        posterPath = input.posterPath,
        adult = false,
        overview = "",
        releaseDate = "",
        id = input.id,
        originalTitle = input.title,
        originalLanguage = "",
        title = input.title,
        backdropPath = "",
        popularity = 5f,
        voteCount = 5,
        video = false,
        voteAverage = input.voteAverage.toFloat()
    )
}