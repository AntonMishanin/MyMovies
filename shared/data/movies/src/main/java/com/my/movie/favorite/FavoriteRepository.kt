package com.my.movie.favorite

import com.my.domain.entity.MovieDetails
import com.my.movie.favorite.dto.FavoriteEntity
import com.my.movie.favorite.storage.FavoriteDao
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FavoriteRepository(
    private val favoriteDao: FavoriteDao
) {
    fun insert(movieDetails: MovieDetails) = favoriteDao.insert(movieDetails.toFavorite())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun deleteById(id: Int) = favoriteDao.deleteById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun loadAll(): Flowable<List<FavoriteEntity>> = favoriteDao.loadAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun isFavoriteById(id: Int, isFavorite: (Boolean) -> Unit): Disposable =
        favoriteDao.loadById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isFavorite(true)
            }, {
                isFavorite(false)
            })
}

fun MovieDetails.toFavorite(): FavoriteEntity = FavoriteEntity(
    id = this.id,
    title = this.title,
    posterPath = this.posterPath,
    rating = this.rating
)