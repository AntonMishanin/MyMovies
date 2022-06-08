package com.my.favorite.data.di

import android.content.Context
import androidx.room.Room
import com.my.favorite.data.FavoriteDataSource
import com.my.favorite.data.FavoriteRepositoryImpl
import com.my.favorite.data.storage.FavoriteDatabase
import com.my.favorite.domain.repository.FavoriteRepository

class FavoriteFactory {
    fun provideRepository(context: Context): FavoriteRepository {
        val database = provideFavoriteDatabase(context)
        val dao = database.getFavoriteDao()
        val favoriteDataSource = FavoriteDataSource(dao)
        return FavoriteRepositoryImpl(favoriteDataSource)
    }

    private fun provideFavoriteDatabase(context: Context): FavoriteDatabase {
        return Room
            .databaseBuilder(context, FavoriteDatabase::class.java, FAVORITE_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    private companion object {
        const val FAVORITE_DATABASE_NAME = "Favorite_database"
    }
}