package com.my.favorite

import android.content.Context
import androidx.room.Room
import com.my.favorite.data.FavoriteDataSource
import com.my.favorite.data.FavoriteRepositoryImpl
import com.my.favorite.data.FavoriteToDboConverter
import com.my.favorite.data.FavoriteToDomainConverter
import com.my.favorite.data.storage.FavoriteDao
import com.my.favorite.data.storage.FavoriteDatabase
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.FetchAllFavoriteUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FavoriteCommonModule {

    @Provides
    internal fun provideFetchAllFavoriteUseCase(favoriteRepository: FavoriteRepository) =
        FetchAllFavoriteUseCase(favoriteRepository)

    @Provides
    internal fun provideIsFavoriteByIdUseCase(favoriteRepository: FavoriteRepository) =
        IsFavoriteByIdUseCase(favoriteRepository)

    @Provides
    internal fun provideSaveMovieToFavoriteUseCase(favoriteRepository: FavoriteRepository) =
        SaveMovieToFavoriteUseCase(favoriteRepository)

    @Provides
    internal fun provideDeleteFromFavoriteByIdUseCase(favoriteRepository: FavoriteRepository) =
        DeleteFromFavoriteByIdUseCase(favoriteRepository)

    @Provides
    internal fun provideFavoriteRepository(
        favoriteDataSource: FavoriteDataSource,
        favoriteToDomainConverter: FavoriteToDomainConverter,
        favoriteToDboConverter: FavoriteToDboConverter
    ): FavoriteRepository =
        FavoriteRepositoryImpl(
            favoriteDataSource,
            favoriteToDomainConverter,
            favoriteToDboConverter
        )

    @Provides
    internal fun provideFavoriteToDomainConverter() = FavoriteToDomainConverter()

    @Provides
    internal fun provideFavoriteToDboConverter() = FavoriteToDboConverter()

    @Provides
    internal fun provideFavoriteDataSource(favoriteDao: FavoriteDao) =
        FavoriteDataSource(favoriteDao)

    @Provides
    internal fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase) =
        favoriteDatabase.getFavoriteDao()

    @Provides
    internal fun provideFavoriteDatabase(
        context: Context,
        @Named(DATABASE_NAME_KEY) name: String
    ): FavoriteDatabase {
        return Room
            .databaseBuilder(context, FavoriteDatabase::class.java, name)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Named(DATABASE_NAME_KEY)
    internal fun provideFavoriteDatabaseName() = "Favorite_database"

    private companion object {
        const val DATABASE_NAME_KEY = "DATABASE_NAME"
    }
}