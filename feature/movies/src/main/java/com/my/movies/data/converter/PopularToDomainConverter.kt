package com.my.movies.data.converter

import com.my.core.converter.IterableConverter
import com.my.movies.data.storage.dto.PopularEntity
import com.my.movies.domain.Movie

internal class PopularToDomainConverter : IterableConverter<PopularEntity, Movie>() {

    override fun convert(input: PopularEntity) = Movie(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage.toDouble(),
        posterPath = input.posterPath
    )
}