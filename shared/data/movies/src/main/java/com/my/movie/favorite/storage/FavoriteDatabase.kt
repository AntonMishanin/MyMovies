package com.my.movie.favorite.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.my.movie.favorite.dto.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
internal abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var instance: FavoriteDatabase? = null

        fun getFavoriteDatabase(context: Context): FavoriteDatabase {
            if (instance != null) {
                return instance as FavoriteDatabase
            }
            synchronized(this) {
                instance = Room
                    .databaseBuilder(context, FavoriteDatabase::class.java, "Favorite_database")
                    .fallbackToDestructiveMigration()
                    .build()

                return instance as FavoriteDatabase
            }
        }
    }
}