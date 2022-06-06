package com.my.movie_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.domain.entity.MovieDetails
import com.my.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.domain.usecase.FetchMovieByIdUseCase
import com.my.domain.usecase.IsFavoriteByIdUseCase
import com.my.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movie_details.entity.MovieEntity
import com.my.resources.mvvm.RxViewModel
import timber.log.Timber

class MovieDetailsViewModel(
    id: String,
    isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
    private val saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
    private val deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
    fetchMovieByIdUseCase: FetchMovieByIdUseCase
) : RxViewModel() {

    private val _movie = MutableLiveData<MovieDetails>()
    val movie: LiveData<MovieDetails> = _movie

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        fetchMovieByIdUseCase(id)
            .subscribe { movie ->
                _movie.value = movie
            }.addToComposite()

        isFavoriteByIdUseCase(id.toInt()) {
            Timber.d(it.toString())
            _isFavorite.value = it
        }.addToComposite()
    }

    fun onFavoriteClicked(isChecked: Boolean) {
        when (isChecked) {
            true -> addToFavorite()
            false -> removeFromFavorite()
        }
    }

    private fun addToFavorite() {
        saveMovieToFavoriteUseCase(_movie.value ?: return).subscribe({
            Timber.d("Success save movie to favorite ${_movie.value}")
        }, {
            Timber.e(it)
        }).addToComposite()
        _isFavorite.value = true
    }

    private fun removeFromFavorite() {
        val id = _movie.value?.id ?: return

        deleteFromFavoriteByIdUseCase(id).subscribe({
            Timber.d("Success delete from favorite by id $id")
        }, {
            Timber.e(it)
        }).addToComposite()

        _isFavorite.value = false
    }

    fun onItemActorClicked(actor: MovieEntity.Actor) {
        Timber.d("On item actor clicked $actor")
    }
}