package com.my.tv_shows.data.converter

import com.my.core.converter.IterableConverter
import com.my.tv_shows.data.remote.dto.TvShowsResponse
import com.my.tv_shows.domain.TvShowsEntity

internal class TvShowsToDomainConverter :
    IterableConverter<TvShowsResponse, List<TvShowsEntity>>() {

    override fun convert(input: TvShowsResponse): List<TvShowsEntity> {
        return input.results?.map {
            TvShowsEntity(
                id = it.id ?: throw NullPointerException("id must be not null"),
                imagePath = "https://image.tmdb.org/t/p/w500" + it.posterPath,// TODO: use BuildConfigWrapper
                title = it.name ?: "",
                rating = it.voteAverage ?: 5f
            )
        } ?: throw NullPointerException("Result must be not null")
    }
}