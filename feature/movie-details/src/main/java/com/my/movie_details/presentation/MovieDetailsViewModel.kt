package com.my.movie_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.movie_details.data.MovieDetailsRepository
import com.my.movie_details.entity.MovieEntity

class MovieDetailsViewModel(
    id: String,
    repository: MovieDetailsRepository
) : ViewModel() {

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> = _movie

    init {
        _movie.value = repository.getMovieById(id)
    }

    fun onFavoriteClicked(isChecked: Boolean) {
    }

    fun onItemActorClicked(actor: MovieEntity.Actor) {
    }
}