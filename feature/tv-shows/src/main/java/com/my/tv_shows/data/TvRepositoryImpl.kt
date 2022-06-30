package com.my.tv_shows.data

import com.my.core.data.OfflineFirstRepository
import com.my.core.di.SchedulersWrapper
import com.my.tv_shows.data.local.LocalDataSource
import com.my.tv_shows.data.local.TvShowsDbo
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.data.remote.dto.TvShowsResponse
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.Flowable
import io.reactivex.Single

internal class TvRepositoryImpl(
    remote: TvRemoteDataSource,
    toDomainException: ToDomainExceptionConverter,
    private val memoryCache: MemoryCacheDataSource,
    localDataSource: LocalDataSource,
    remoteToLocalConverter: RemoteToLocalConverter,
    localToDomainConverter: LocalToDomainConverter,
    schedulersWrapper: SchedulersWrapper
) : TvRepository, OfflineFirstRepository<List<TvShowsEntity>, TvShowsResponse, List<TvShowsDbo>>(
    schedulersWrapper = schedulersWrapper,
    observeFromMemory = memoryCache::observable,
    fetchFromStorage = localDataSource::fetch,
    fetchFromNetwork = remote::fetchPopularTvShows,
    saveToStorage = localDataSource::insert,
    saveToMemory = memoryCache::add,
    networkToStorage = remoteToLocalConverter::convert,
    storageToDomain = localToDomainConverter::convert,
    toDomainException = toDomainException::convert
) {

    override fun observeTvShows(): Flowable<List<TvShowsEntity>> = observable()

    override fun fetchCachedTvShows(): Single<List<TvShowsEntity>> = memoryCache.read()

    override fun fetchFreshTvShows(): Single<List<TvShowsEntity>> = refresh()

    override fun saveToCachedTvShows(tvShows: List<TvShowsEntity>) = memoryCache.replace(tvShows)
}