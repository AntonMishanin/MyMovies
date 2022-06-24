package com.my.tv_shows.presentation

import com.my.core.domain.Response
import com.my.core.mvp.RxPresenter
import com.my.tv_shows.domain.FetchPopularTvShowsUseCase
import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.functions.Consumer
import timber.log.Timber

class TvShowsPresenter(
    private val fetchPopularTvShowsUseCase: FetchPopularTvShowsUseCase
) : RxPresenter<TvShowsView>() {

    override fun onViewReady() {
        fetchPopularTvShowsUseCase()
            .subscribe(Consumer {
                when (it) {
                    is Response.Success -> view?.setTvShowsList(it.value)
                    is Response.Error -> {
                    } // TODO(): show error state
                }
            }).addToComposite()
    }

    fun onItemTvShowClicked(tvShowsEntity: TvShowsEntity) {
        Timber.d("On item tv show clicked $tvShowsEntity")
    }
}