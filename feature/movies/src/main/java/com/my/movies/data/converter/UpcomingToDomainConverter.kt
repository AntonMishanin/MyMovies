package com.my.movies.data.converter

import com.my.core.converter.IterableConverter
import com.my.movies.data.storage.dto.UpcomingEntity
import com.my.movies.domain.Movie

internal class UpcomingToDomainConverter : IterableConverter<UpcomingEntity, Movie>() {

    override fun convert(input: UpcomingEntity) = Movie(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage.toDouble(),
        posterPath = input.posterPath
    )
}