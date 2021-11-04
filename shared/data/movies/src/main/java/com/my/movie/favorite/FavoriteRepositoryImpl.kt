package com.my.movie.favorite

import com.my.domain.entity.MovieDetails
import com.my.movie.favorite.storage.FavoriteDao
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

internal class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun insert(movieDetails: MovieDetails) = favoriteDao.insert(movieDetails.toFavorite())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun deleteById(id: Int) = favoriteDao.deleteById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun loadAll(): Flowable<List<MovieDetails>> = favoriteDao.loadAll()
        .subscribeOn(Schedulers.io())
        .map { favoritesList ->
            favoritesList.map { it.toMovieDetails() }
        }
        .observeOn(AndroidSchedulers.mainThread())

    override fun isFavoriteById(id: Int, isFavorite: (Boolean) -> Unit): Disposable =
        favoriteDao.loadById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isFavorite(true)
            }, {
                isFavorite(false)
            })
}