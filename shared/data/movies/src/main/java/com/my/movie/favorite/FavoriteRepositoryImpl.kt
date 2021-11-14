package com.my.movie.favorite

import com.my.domain.entity.MovieDetails
import com.my.domain.repository.FavoriteRepository
import com.my.movie.favorite.storage.FavoriteDao
import io.reactivex.Flowable
import io.reactivex.Single

internal class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun insert(movieDetails: MovieDetails) = favoriteDao.insert(movieDetails.toFavorite())

    override fun deleteById(id: Int) = favoriteDao.deleteById(id)

    override fun loadAll(): Flowable<List<MovieDetails>> = favoriteDao.loadAll()
        .map { favoritesList ->
            favoritesList.map { it.toMovieDetails() }
        }

    override fun isFavoriteById(id: Int): Single<MovieDetails> =
        favoriteDao.loadById(id).map { it.toMovieDetails() }
}