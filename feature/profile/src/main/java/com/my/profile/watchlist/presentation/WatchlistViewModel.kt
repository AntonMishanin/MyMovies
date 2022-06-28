package com.my.profile.watchlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.core.presentation.BaseViewModel
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.profile.watchlist.domain.FetchWatchlistUseCase
import com.xwray.groupie.viewbinding.BindableItem
import timber.log.Timber

internal class WatchlistViewModel(
    fetchWatchlistUseCase: FetchWatchlistUseCase,
    watchlistConverter: WatchlistConverter
) : BaseViewModel() {

    private val privateContent: MutableLiveData<List<BindableItem<*>>> = MutableLiveData()
    val content: LiveData<List<BindableItem<*>>> = privateContent

    init {
        fetchWatchlistUseCase.invoke()
            //TODO: use schedulers
            .doOnSubscribe { privateContent.value = watchlistConverter.progress() }
            .subscribe({
                privateContent.value = watchlistConverter.convert(it, ::onMovieItemClicked)
            }, {
                privateContent.value = watchlistConverter.convert(it)
            }).addToComposite()
    }

    private fun onMovieItemClicked(movie: FavoriteEntity) {
        Timber.d("On movie item clicked $movie")
    }
}