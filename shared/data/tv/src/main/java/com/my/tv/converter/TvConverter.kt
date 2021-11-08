package com.my.tv.converter

import com.my.domain.entity.TvShowsEntity
import com.my.tv.remote.dto.TvShowDto

internal object TvConverter {
    fun toValueObject(dto: TvShowDto): TvShowsEntity = TvShowsEntity(
        id = dto.id ?: 0,
        imagePath = dto.posterPath ?: "",
        title = dto.name ?: "",
        rating = dto.voteAverage ?: 5f
    )
}