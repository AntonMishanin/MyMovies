package com.my.tv_shows.presentation

import com.my.domain.entity.TvShowsEntity
import com.my.domain.usecase.FetchPopularTvShowsUseCase
import com.my.resources.mvp.RxPresenter
import timber.log.Timber

class TvShowsPresenter(
    private val fetchPopularTvShowsUseCase: FetchPopularTvShowsUseCase
) : RxPresenter<TvShowsView>() {

    override fun onViewReady() {
        fetchPopularTvShowsUseCase()
            .subscribe({
                view?.setTvShowsList(it)
            }, {
                Timber.d(it)
            }).addToComposite()
    }

    fun onItemTvShowClicked(tvShowsEntity: TvShowsEntity) {
    }
}