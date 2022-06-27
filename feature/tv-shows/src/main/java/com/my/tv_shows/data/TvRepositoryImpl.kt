package com.my.tv_shows.data

import com.my.tv_shows.data.converter.ToDomainExceptionConverter
import com.my.tv_shows.data.converter.TvShowsToDomainConverter
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvShowsEntity

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource,
    private val toDomainConverter: TvShowsToDomainConverter,
    private val toDomainException: ToDomainExceptionConverter,
    private val memoryCache: MemoryCacheDataSource
) : TvRepository {

    override fun observeTvShows() = memoryCache.observable()

    override fun fetchFreshTvShows() = remote.fetchPopularTvShows()
        .map { response ->
            val result = toDomainConverter.convert(response)
            memoryCache.save(result)
            result
        }
        .onErrorReturn { throwable ->
            throw toDomainException.convert(throwable)
        }

    override fun fetchCachedTvShows() = memoryCache.read()

    override fun saveToCachedTvShows(tvShows: List<TvShowsEntity>) {
        memoryCache.save(tvShows)
    }
}