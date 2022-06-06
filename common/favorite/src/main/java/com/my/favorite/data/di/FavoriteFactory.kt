package com.my.favorite.data.di

import android.content.Context
import com.my.favorite.data.FavoriteRepositoryImpl
import com.my.favorite.data.storage.FavoriteDatabase
import com.my.favorite.domain.repository.FavoriteRepository

class FavoriteFactory {
    fun provideRepository(context: Context): FavoriteRepository {
        val database = FavoriteDatabase.getFavoriteDatabase(context)
        val dao = database.getFavoriteDao()
        return FavoriteRepositoryImpl(dao)
    }
}