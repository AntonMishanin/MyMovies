package com.my.tv_shows.data

import com.my.tv_shows.data.converter.TvShowsToDomainConverter
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.Single

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource,
    private val toDomainConverter: TvShowsToDomainConverter
) : TvRepository {

    override fun fetchPopularTvShows(): Single<List<TvShowsEntity>> = remote.fetchPopularTvShows()
        .map { toDomainConverter.convert(it.results ?: emptyList()) }
}