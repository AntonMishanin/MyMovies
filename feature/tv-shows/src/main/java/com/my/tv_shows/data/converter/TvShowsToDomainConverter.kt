package com.my.tv_shows.data.converter

import com.my.core.converter.Converter
import com.my.core.converter.IterableConverter
import com.my.core.domain.NoInternetConnectionException
import com.my.core.domain.UnknownException
import com.my.tv_shows.data.remote.dto.TvShowsResponse
import com.my.tv_shows.domain.TvShowsEntity
import java.net.UnknownHostException

internal class TvShowsToDomainConverter :
    IterableConverter<TvShowsResponse, List<TvShowsEntity>>() {

    override fun convert(input: TvShowsResponse): List<TvShowsEntity> {
        return input.results?.map {
            TvShowsEntity(
                id = it.id ?: throw NullPointerException("id must be not null"),
                imagePath = "https://image.tmdb.org/t/p/w500" + it.posterPath,// TODO: use BuildConfigWrapper
                title = it.name ?: "",
                rating = it.voteAverage ?: 5f,
                overview = it.overview ?: "",
                isOverviewVisible = false
            )
        } ?: throw NullPointerException("Result must be not null")
    }
}

internal class ToDomainExceptionConverter : Converter<Throwable, Exception> {

    override fun convert(input: Throwable): Exception {
        return when (input) {
            is UnknownHostException -> NoInternetConnectionException()
            else -> UnknownException()
        }
    }
}