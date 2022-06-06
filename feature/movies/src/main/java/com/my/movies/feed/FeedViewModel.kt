package com.my.movies.feed

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.core.mvvm.RxViewModel
import com.my.movies.domain.CompositeMovieEntity
import com.my.movies.domain.FetchCompositeMovieUseCase
import com.my.movies.domain.Movie
import com.my.movies.feed.state.NavigationState

private const val MIN_SEARCH_LENGTH = 3

class FeedViewModel(
    fetchCompositeMovieUseCase: FetchCompositeMovieUseCase
) : RxViewModel() {

    private val _content: MutableLiveData<CompositeMovieEntity> = MutableLiveData()
    val content: LiveData<CompositeMovieEntity> = _content

    private val _navigation: MutableLiveData<NavigationState> = MutableLiveData()
    val navigation: LiveData<NavigationState> = _navigation

    init {
        fetchCompositeMovieUseCase.invoke()
            .subscribe {
                _content.value = it
            }.addToComposite()
    }

    fun onSearchTextChanged(text: Editable?) {
        if (text.isValid()) {
            _navigation.value = NavigationState.Search(searchText = text.toString())
        }
    }

    private fun Editable?.isValid() = this.toString().length > MIN_SEARCH_LENGTH

    fun onMovieItemClicked(movie: Movie) {
        _navigation.value = NavigationState.MovieDetails(id = movie.id.toString())
    }

    fun onNavigationSuccess() {
        _navigation.value = NavigationState.None
    }
}