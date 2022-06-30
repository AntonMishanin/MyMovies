package com.my.tv_shows.domain

import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

internal class TvSeasonsInteractor(
    private val repository: TvRepository,
    private var paginationConfig: PaginationConfig
) {

    private val loadMoreObservable = PublishSubject.create<Pair<Int, Int>>()

    init {
        loadMoreObservable
            .filter {
                val currentPosition = it.first
                val itemsSize = it.second - paginationConfig.prefetchDistance
                currentPosition >= itemsSize
            }
            .throttleFirst(THROTTLE_DURATION_MILLISECONDS, TimeUnit.MILLISECONDS)
            .filter { !paginationConfig.isProgress }
            .doOnNext {
                val newPage = paginationConfig.page + paginationConfig.jumpRange
                paginationConfig = paginationConfig.copy(page = newPage, isProgress = true)
            }
            .flatMap { repository.fetchFreshTvShows(paginationConfig).toObservable() }
            .doOnNext { paginationConfig = paginationConfig.copy(isProgress = false) }
            .subscribe()
    }

    fun loadMore(currentPosition: Int, itemsSize: Int) =
        loadMoreObservable.onNext(Pair(currentPosition, itemsSize))

    fun toggleOverview(tvShowsEntity: TvShowsEntity): Single<List<TvShowsEntity>> {
        return repository.fetchCachedTvShows()
            .toObservable()
            .flatMapIterable {
                it
            }
            .map {
                return@map if (it == tvShowsEntity) {
                    val isOverviewVisible = tvShowsEntity.isOverviewVisible
                    tvShowsEntity.copy(isOverviewVisible = !isOverviewVisible)
                } else {
                    it
                }
            }
            .toList()
            .doOnSuccess { repository.saveToCachedTvShows(it) }
    }

    fun refresh() = repository.fetchFreshTvShows(paginationConfig)

    fun observable() = repository.observeTvShows()

    companion object {
        private const val THROTTLE_DURATION_MILLISECONDS = 1000L
    }
}