package com.my.tv_shows.data

import com.my.core.di.SchedulersWrapper
import com.my.tv_shows.data.local.LocalDataSource
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.Single

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource,
    private val toDomainConverter: TvShowsToDomainConverter,
    private val toDomainException: ToDomainExceptionConverter,
    private val memoryCache: MemoryCacheDataSource,
    private val localDataSource: LocalDataSource,
    private val remoteToLocalConverter: RemoteToLocalConverter,
    private val localToDomainConverter: LocalToDomainConverter,
    private val schedulersWrapper: SchedulersWrapper
) : TvRepository {

    override fun observeTvShows() = memoryCache.observable()

    override fun fetchFreshTvShows(): Single<List<TvShowsEntity>> {
        val type = "popular"//TODO: send to api
        return remote.fetchPopularTvShows()
            .flatMap {
                val tvShows = remoteToLocalConverter.convert(it, type)
                localDataSource.insert(tvShows).toSingleDefault(it)
            }
            .map { toDomainConverter.convert(it) }
            .doOnSuccess { memoryCache.save(it) }
            .onErrorResumeNext { fetchLocal(it) }
            .onErrorReturn { throwable ->
                throwable.printStackTrace()
                throw toDomainException.convert(throwable)
            }
            .subscribeOn(schedulersWrapper.io())
    }

    override fun fetchCachedTvShows() = memoryCache.read()

    override fun saveToCachedTvShows(tvShows: List<TvShowsEntity>) {
        memoryCache.save(tvShows)
    }

    private fun fetchLocal(throwable: Throwable): Single<List<TvShowsEntity>> {
        return localDataSource.fetch()
            .map { localToDomainConverter.convert(it) }
            .doOnSuccess { memoryCache.save(it) }
            .map {
                when (it.isEmpty()) {
                    true -> throw throwable
                    false -> it
                }
            }
    }
}