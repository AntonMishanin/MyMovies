package com.my.tv_shows.presentation

import com.my.domain.entity.TvShowsEntity
import com.my.domain.usecase.FetchPopularTvShowsUseCase
import com.my.tv_shows.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class TvShowsPresenter(
    private val fetchPopularTvShowsUseCase: FetchPopularTvShowsUseCase
) : BasePresenter<TvShowsView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onViewReady() {
        val d = fetchPopularTvShowsUseCase()
            .subscribe({
                view?.setTvShowsList(it)
            }, {
                Timber.d(it)
            })
        compositeDisposable.add(d)
    }

    override fun onViewDetached() {
        compositeDisposable.clear()
    }

    fun onItemTvShowClicked(tvShowsEntity: TvShowsEntity) {
    }
}