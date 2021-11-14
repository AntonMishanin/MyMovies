package com.my.movie_details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.domain.entity.MovieDetails
import com.my.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.domain.usecase.FetchMovieByIdUseCase
import com.my.domain.usecase.IsFavoriteByIdUseCase
import com.my.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movie_details.entity.MovieEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MovieDetailsViewModel(
    id: String,
    isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
    private val saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
    private val deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
    fetchMovieByIdUseCase: FetchMovieByIdUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _movie = MutableLiveData<MovieDetails>()
    val movie: LiveData<MovieDetails> = _movie

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        fetchMovieByIdUseCase(id)
            .subscribe { movie ->
                _movie.value = movie
            }.toComposite()

        isFavoriteByIdUseCase(id.toInt()) {
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
        saveMovieToFavoriteUseCase(_movie.value ?: return).subscribe({
            Timber.d("SUCCESS INSERT")
        }, {
            Timber.d("ERROR INSERT")
        }).toComposite()
        _isFavorite.value = true
    }

    private fun removeFromFavorite() {
        val id = _movie.value?.id ?: return

        deleteFromFavoriteByIdUseCase(id).subscribe({
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