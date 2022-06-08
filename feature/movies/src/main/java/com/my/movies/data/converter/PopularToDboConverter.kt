package com.my.movies.data.converter

import com.my.core.converter.IterableConverter
import com.my.movies.data.storage.dto.PopularEntity
import com.my.movies.domain.Movie

internal class PopularToDboConverter : IterableConverter<Movie, PopularEntity>() {

    override fun convert(input: Movie) = PopularEntity(
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