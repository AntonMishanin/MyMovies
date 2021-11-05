package com.my.movie_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.domain.entity.MovieDetails
import com.my.movie.MovieRepository
import com.my.movie.favorite.FavoriteRepository
import com.my.movie_details.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MovieDetailsViewModel(
    id: String,
    private val favoriteRepository: FavoriteRepository,
    movieRepository: MovieRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movie = MutableLiveData<MovieDetails>()
    val movie: LiveData<MovieDetails> = _movie

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        movieRepository.fetchMovieById(id)
            .subscribe { movie ->
                _movie.value = movie
            }.toComposite()

        favoriteRepository.isFavoriteById(id.toInt()) {
            Timber.d(it.toString())
            _isFavorite.value = it
        }.toComposite()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun onFavoriteClicked(isChecked: Boolean) {
        when (isChecked) {
            true -> addToFavorite()
            false -> removeFromFavorite()
        }
    }

    private fun addToFavorite() {
        favoriteRepository.insert(_movie.value ?: return).subscribe({
            Timber.d("SUCCESS INSERT")
        }, {
            Timber.d("ERROR INSERT")
        }).toComposite()
        _isFavorite.value = true
    }

    private fun removeFromFavorite() {
        val id = _movie.value?.id ?: return

        favoriteRepository.deleteById(id).subscribe({
            Timber.d("SUCCESS DELETE")
        }, {
            Timber.d("ERROR DELETE")
        }).toComposite()

        _isFavorite.value = false
    }

    fun onItemActorClicked(actor: MovieEntity.Actor) {
    }

    private fun Disposable.toComposite() {
        compositeDisposable.add(this)
    }
}