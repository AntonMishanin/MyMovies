package com.my.movies.data.converter

import com.my.core.converter.IterableConverter
import com.my.movies.data.storage.dto.NowPlayingEntity
import com.my.movies.domain.Movie

internal class NowPlayingToDomainConverter : IterableConverter<NowPlayingEntity, Movie>() {

    override fun convert(input: NowPlayingEntity) = Movie(
        id = input.id,
        title = input.title,
        voteAverage = input.voteAverage.toDouble(),
        posterPath = input.posterPath
    )
}