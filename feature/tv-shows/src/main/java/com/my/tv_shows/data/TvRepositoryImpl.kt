package com.my.tv_shows.data

import com.my.tv_shows.data.converter.ToDomainExceptionConverter
import com.my.tv_shows.data.converter.TvShowsToDomainConverter
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource,
    private val toDomainConverter: TvShowsToDomainConverter,
    private val toDomainException: ToDomainExceptionConverter
) : TvRepository {

    override fun fetchPopularTvShows() = remote.fetchPopularTvShows()
        .map {
            toDomainConverter.convert(it)
        }
        .onErrorReturn {
            throw toDomainException.convert(it)
        }
}