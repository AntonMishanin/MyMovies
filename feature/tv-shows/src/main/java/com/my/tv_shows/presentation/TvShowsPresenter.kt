package com.my.tv_shows.presentation

import com.my.core.di.SchedulersWrapper
import com.my.core.mvp.RxPresenter
import com.my.tv_shows.domain.ObserveTvShowsUseCase
import com.my.tv_shows.domain.RefreshTvShowsUseCase
import com.my.tv_shows.domain.ToggleOverviewUseCase
import com.my.tv_shows.domain.TvShowsEntity
import com.my.tv_shows.ui.RefreshCallback
import com.my.tv_shows.ui.ToggleOverviewCallback
import com.xwray.groupie.viewbinding.BindableItem

internal class TvShowsPresenter(
    observeTvShowsUseCase: ObserveTvShowsUseCase,
    private val toggleOverviewUseCase: ToggleOverviewUseCase,
    private val refreshTvShowsUseCase: RefreshTvShowsUseCase,
    private val tvShowsUiConverter: TvShowsUiConverter,
    private val schedulersWrapper: SchedulersWrapper
) : RxPresenter<TvShowsView>(), RefreshCallback, ToggleOverviewCallback {

    private var state: List<BindableItem<*>> = tvShowsUiConverter.progress()
        set(value) {
            field = value
            view?.showState(value)
        }

    init {
        observeTvShowsUseCase.invoke()
            .observeOn(schedulersWrapper.ui())
            .doOnSubscribe {
                state = tvShowsUiConverter.progress()
            }
            .subscribe({ content ->
                state = tvShowsUiConverter.convert(content, toggleOverviewCallback = this)
            }, { throwable ->
                state = tvShowsUiConverter.convert(throwable, refreshCallback = this)
            })
            .addToComposite()
    }

    override fun onViewReady() {
        view?.showState(state)
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
                state = tvShowsUiConverter.progress()
            }
            .subscribe({}, { throwable ->
                state = tvShowsUiConverter.convert(throwable, refreshCallback = this)
            })
            .addToComposite()
    }
}