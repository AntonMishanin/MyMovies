package com.my.movie.favorite.di

import android.content.Context
import com.my.movie.favorite.storage.FavoriteDatabase
import com.my.movie.favorite.FavoriteRepository

class FavoriteFactory {
    fun provideRepository(context: Context): FavoriteRepository {
        val database = FavoriteDatabase.getFavoriteDatabase(context)
        val dao = database.getFavoriteDao()
        return FavoriteRepository(dao)
    }
}