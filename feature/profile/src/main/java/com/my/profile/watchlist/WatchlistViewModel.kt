package com.my.profile.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.domain.entity.Movie
import com.my.resources.mvvm.RxViewModel
import timber.log.Timber

class WatchlistViewModel : RxViewModel() {

    private val _content: MutableLiveData<List<Movie>> = MutableLiveData()
    val content: LiveData<List<Movie>> = _content

    init {
        _content.value = emptyList()
    }

    fun onMovieItemClicked(movie: Movie) {
        Timber.d("On movie item clicked $movie")
    }
}