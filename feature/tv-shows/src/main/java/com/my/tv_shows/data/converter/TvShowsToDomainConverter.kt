package com.my.tv_shows.data.converter

import com.my.core.converter.IterableConverter
import com.my.tv_shows.data.remote.dto.TvShowDto
import com.my.tv_shows.domain.TvShowsEntity

internal class TvShowsToDomainConverter : IterableConverter<TvShowDto, TvShowsEntity>() {

    override fun convert(input: TvShowDto) = TvShowsEntity(
        id = input.id ?: throw NullPointerException("id must be not null"),
        imagePath = "https://image.tmdb.org/t/p/w500" + input.posterPath,// TODO: use BuildConfigWrapper
        title = input.name ?: "",
        rating = input.voteAverage ?: 5f
    )
}