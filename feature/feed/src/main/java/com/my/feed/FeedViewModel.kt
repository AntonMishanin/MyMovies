package com.my.feed

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.domain.entity.Movie
import com.my.feed.state.NavigationState
import com.my.movie.MovieRepository
import io.reactivex.disposables.CompositeDisposable

private const val MIN_SEARCH_LENGTH = 3

class FeedViewModel(
    movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _nowPlaying: MutableLiveData<List<Movie>> = MutableLiveData()
    val nowPlaying: LiveData<List<Movie>> = _nowPlaying

    private val _popular: MutableLiveData<List<Movie>> = MutableLiveData()
    val popular: LiveData<List<Movie>> = _popular

    private val _upcoming: MutableLiveData<List<Movie>> = MutableLiveData()
    val upcoming: LiveData<List<Movie>> = _upcoming

    private val _navigation: MutableLiveData<NavigationState> = MutableLiveData()
    val navigation: LiveData<NavigationState> = _navigation

    init {
        compositeDisposable.add(
            movieRepository
                .fetchNowPlaying()
                .subscribe(_nowPlaying::setValue)
        )
        compositeDisposable.add(
            movieRepository
                .fetchPopular()
                .subscribe(_popular::setValue)
        )
        compositeDisposable.add(
            movieRepository
                .fetchUpcoming()
                .subscribe(_upcoming::setValue)
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun onSearchTextChanged(text: Editable?) {
        if (text.isNotShort()) {
            _navigation.value = NavigationState.Search(searchText = text.toString())
        }
    }

    private fun Editable?.isNotShort() = this.toString().length > MIN_SEARCH_LENGTH

    fun onMovieItemClicked(movie: Movie) {
        _navigation.value = NavigationState.MovieDetails(id = movie.title)
    }

    fun onNavigationSuccess() {
        _navigation.value = NavigationState.None
    }
}