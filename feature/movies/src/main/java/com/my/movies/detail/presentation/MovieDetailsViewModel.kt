package com.my.movies.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.my.core.presentation.BaseViewModel
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.detail.entity.MovieEntity
import com.my.movies.domain.FetchMovieByIdUseCase
import com.my.movies.domain.MovieDetails
import timber.log.Timber

internal class MovieDetailsViewModel(
    id: String,
    isFavoriteByIdUseCase: IsFavoriteByIdUseCase,
    private val saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
    private val deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
    fetchMovieByIdUseCase: FetchMovieByIdUseCase
) : BaseViewModel() {

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
        saveMovieToFavoriteUseCase(
            _movie.value?.toFavorite() ?: return
        ).subscribe({
            Timber.d("Success save movie to favorite ${_movie.value}")
            _isFavorite.value = true
        }, {
            Timber.e(it)
        }).addToComposite()
    }

    private fun removeFromFavorite() {
        val id = _movie.value?.id ?: return

        deleteFromFavoriteByIdUseCase(id).subscribe({
            Timber.d("Success delete from favorite by id $id")
            _isFavorite.value = false
        }, {
            Timber.e(it)
        }).addToComposite()
    }

    fun onItemActorClicked(actor: MovieEntity.Actor) {
        Timber.d("On item actor clicked $actor")
    }

    private fun MovieDetails.toFavorite() = FavoriteEntity(
        id = this.id,
        title = this.title,
        overview = "this.overview",
        posterPath = this.posterPath,
        rating = this.rating,
        studio = this.studio,
        genre = this.genre,
        year = this.year
    )
}