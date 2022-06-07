package com.my.profile.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.core.presentation.BaseViewModel
import com.my.favorite.domain.usecase.FavoriteEntity
import timber.log.Timber

class WatchlistViewModel : BaseViewModel() {

    private val _content: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()
    val content: LiveData<List<FavoriteEntity>> = _content

    init {
        _content.value = emptyList()
    }

    fun onMovieItemClicked(movie: FavoriteEntity) {
        Timber.d("On movie item clicked $movie")
    }
}