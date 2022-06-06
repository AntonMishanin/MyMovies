package com.my.tv_shows.data.converter

import com.my.tv_shows.data.remote.dto.TvShowDto
import com.my.tv_shows.domain.TvShowsEntity

internal object TvConverter {
    fun toValueObject(dto: TvShowDto): TvShowsEntity = TvShowsEntity(
        id = dto.id ?: throw NullPointerException("id must not be null"),
        imagePath = dto.posterPath ?: "",
        title = dto.name ?: "",
        rating = dto.voteAverage ?: 5f
    )
}