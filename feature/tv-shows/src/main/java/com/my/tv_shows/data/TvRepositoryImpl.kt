package com.my.tv_shows.data

import com.my.core.data.HandleResponse
import com.my.tv_shows.data.converter.TvShowsToDomainConverter
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvShowsEntity

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource,
    private val toDomainConverter: TvShowsToDomainConverter,
    private val handleResponse: HandleResponse<List<TvShowsEntity>>
) : TvRepository {

    override fun fetchPopularTvShows() = remote.fetchPopularTvShows()
        .map {
            handleResponse.handleResult(it, toDomainConverter)
        }
        .onErrorReturn(handleResponse::handleResult)
}