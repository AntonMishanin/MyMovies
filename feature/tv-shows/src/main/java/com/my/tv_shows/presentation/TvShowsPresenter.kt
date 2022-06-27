package com.my.tv_shows.presentation

import com.my.core.mvp.RxPresenter
import com.my.tv_shows.domain.FetchPopularTvShowsUseCase
import com.my.tv_shows.domain.TvShowsEntity
import com.my.tv_shows.ui.OnItemClickedCallback
import com.my.tv_shows.ui.OnTryAgainClickedCallback

internal class TvShowsPresenter(
    private val fetchPopularTvShowsUseCase: FetchPopularTvShowsUseCase,
    private val tvShowsUiConverter: TvShowsUiConverter
) : RxPresenter<TvShowsView>(), OnTryAgainClickedCallback, OnItemClickedCallback {

    override fun onViewReady() {
        onTryAgainClicked()
    }

    // Invoke from TvShowsItem
    override fun onItemClicked(tvShowsEntity: TvShowsEntity) {

    }

    // Invoke from UnknownErrorItem
    override fun onTryAgainClicked() {
        fetchPopularTvShowsUseCase()
            .doOnSubscribe {
                view?.showState(tvShowsUiConverter.progress())
            }
            .subscribe({ content ->
                val state = tvShowsUiConverter.convert(content, onItemClickedCallback = this)
                view?.showState(state)
            }, { throwable ->
                val state = tvShowsUiConverter.convert(throwable, tryAgainClickedCallback = this)
                view?.showState(state)
            }).addToComposite()
    }
}