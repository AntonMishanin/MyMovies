package com.my.tv_shows.presentation

import com.my.core.mvp.RxPresenter
import com.my.tv_shows.domain.TvShowsEntity
import timber.log.Timber

class TvShowsPresenter : RxPresenter<TvShowsView>() {

    override fun onViewReady() {
        // fetchPopularTvShowsUseCase()
        //     .subscribe({
        //         view?.setTvShowsList(it)
        //     }, {
        //         Timber.e(it)
        //     }).addToComposite()
    }

    fun onItemTvShowClicked(tvShowsEntity: TvShowsEntity) {
        Timber.d("On item tv show clicked $tvShowsEntity")
    }
}