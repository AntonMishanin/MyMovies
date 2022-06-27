package com.my.tv_shows.data

import com.my.core.converter.IterableConverter
import com.my.tv_shows.data.local.TvShowsDbo
import com.my.tv_shows.data.remote.dto.TvShowDto
import com.my.tv_shows.data.remote.dto.TvShowsResponse
import com.my.tv_shows.domain.TvShowsEntity

internal class RemoteToLocalConverter {

    fun convert(input: TvShowsResponse, type: String) = input.results?.map { convert(it, type) }
        ?: throw IllegalArgumentException("TvShowsResponse must be not null")

    private fun convert(source: TvShowDto, type: String) = TvShowsDbo(
        id = source.id ?: throw IllegalArgumentException("Id must be not null"),
        type = type,
        posterPath = source.posterPath ?: "",
        popularity = source.popularity ?: 5f,
        backdropPath = source.backdropPath ?: "",
        voteAverage = source.voteAverage ?: 5f,
        overview = source.overview ?: "",
        firstAirDate = source.firstAirDate ?: "",
        originalLanguage = source.originalLanguage ?: "",
        voteCount = source.voteCount ?: 5,
        name = source.name ?: "",
        originalName = source.originalName ?: ""
    )
}

internal class LocalToDomainConverter : IterableConverter<TvShowsDbo, TvShowsEntity>() {

    override fun convert(input: TvShowsDbo) = TvShowsEntity(
        id = input.id,
        imagePath = "https://image.tmdb.org/t/p/w500" + input.posterPath,// TODO: use BuildConfigWrapper
        title = input.name,
        rating = input.voteAverage,
        overview = input.overview,
        isOverviewVisible = false
    )
}