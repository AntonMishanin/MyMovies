package com.my.favorite.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.favorite.data.dto.FavoriteDbo

@Database(entities = [FavoriteDbo::class], version = 1, exportSchema = false)
internal abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao
}