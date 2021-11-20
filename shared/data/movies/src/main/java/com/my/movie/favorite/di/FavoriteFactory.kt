package com.my.movie.favorite.di

import android.content.Context
import com.my.domain.repository.FavoriteRepository
import com.my.movie.favorite.FavoriteRepositoryImpl
import com.my.movie.favorite.storage.FavoriteDatabase

class FavoriteFactory {
    fun provideRepository(context: Context): FavoriteRepository {
        val database = FavoriteDatabase.getFavoriteDatabase(context)
        val dao = database.getFavoriteDao()
        return FavoriteRepositoryImpl(dao)
    }
}