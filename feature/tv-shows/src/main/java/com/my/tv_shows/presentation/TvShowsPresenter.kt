package com.my.tv_shows.presentation

import com.my.core.di.SchedulersWrapper
import com.my.core.mvp.RxPresenter
import com.my.tv_shows.domain.ObserveTvShowsUseCase
import com.my.tv_shows.domain.RefreshTvShowsUseCase
import com.my.tv_shows.domain.ToggleOverviewUseCase
import com.my.tv_shows.domain.TvShowsEntity
import com.my.tv_shows.ui.RefreshCallback
import com.my.tv_shows.ui.ToggleOverviewCallback

internal class TvShowsPresenter(
    observeTvShowsUseCase: ObserveTvShowsUseCase,
    private val toggleOverviewUseCase: ToggleOverviewUseCase,
    private val refreshTvShowsUseCase: RefreshTvShowsUseCase,
    private val tvShowsUiConverter: TvShowsUiConverter,
    private val schedulersWrapper: SchedulersWrapper
) : RxPresenter<TvShowsView>(), RefreshCallback, ToggleOverviewCallback {

    init {
        observeTvShowsUseCase.invoke()
            .observeOn(schedulersWrapper.ui())
            .doOnSubscribe {
                view?.showState(tvShowsUiConverter.progress())
            }
            .subscribe({ content ->
                val state = tvShowsUiConverter.convert(content, toggleOverviewCallback = this)
                view?.showState(state)
            }, { throwable ->
                val state = tvShowsUiConverter.convert(throwable, refreshCallback = this)
                view?.showState(state)
            })
            .addToComposite()
    }

    override fun onViewReady() {
        onRefreshClicked()
    }

    // Invoke from TvShowsItem
    override fun onToggleOverviewClicked(tvShowsEntity: TvShowsEntity) {
        toggleOverviewUseCase.invoke(tvShowsEntity)
            .subscribe()
            .addToComposite()
    }

    // Invoke from UnknownErrorItem
    override fun onRefreshClicked() {
        refreshTvShowsUseCase.invoke()
            .observeOn(schedulersWrapper.ui())
            .doOnSubscribe {
                view?.showState(tvShowsUiConverter.progress())
            }
            .subscribe()
            .addToComposite()
    }
}