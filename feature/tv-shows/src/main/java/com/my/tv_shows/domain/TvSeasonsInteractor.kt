package com.my.tv_shows.domain

import com.my.core.pagination.Pagination
import io.reactivex.Single

internal class TvSeasonsInteractor(
    private val repository: TvRepository,
    private var pagination: Pagination
) {

    init {
        pagination.paginationCallback { page ->
            repository.fetchFreshTvShows(page).toObservable()
        }
    }

    fun loadMore(currentPosition: Int, itemsSize: Int) =
        pagination.loadMoreRequest(currentPosition, itemsSize)

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

    fun refresh() = repository.fetchFreshTvShows(pagination.page)

    fun observable() = repository.observeTvShows()
}