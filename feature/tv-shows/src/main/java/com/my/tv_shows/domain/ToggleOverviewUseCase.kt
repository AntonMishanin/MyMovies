package com.my.tv_shows.domain

import io.reactivex.Single

internal class ToggleOverviewUseCase(
    private val repository: TvRepository
) {

    fun invoke(tvShowsEntity: TvShowsEntity): Single<List<TvShowsEntity>> {
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
}