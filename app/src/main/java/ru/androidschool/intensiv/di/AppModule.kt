package ru.androidschool.intensiv.di

import android.app.Application
import android.content.Context
import com.my.core.di.AppScope
import com.my.core.di.BuildConfigWrapper
import com.my.favorite.data.di.FavoriteFactory
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.detail.di.MovieDetailsDependencies
import com.my.movies.feed.di.FeedDependencies
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.androidschool.intensiv.BuildConfig

@Module
internal class AppModule {

    @[Provides AppScope]
    internal fun provideFeedDependencies(
        context: Context,
        retrofit: Retrofit
    ) = object : FeedDependencies {

        override fun provideContext() = context

        override fun provideRetrofit() = retrofit
    }

    @[Provides AppScope]
    internal fun provideMovieDetailsDependencies(
        context: Context,
        favoriteRepository: FavoriteRepository,
        retrofit: Retrofit
    ) = object : MovieDetailsDependencies {

        override fun provideContext() = context

        override fun provideDeleteFromFavoriteByIdUseCase() =
            DeleteFromFavoriteByIdUseCase(favoriteRepository)

        override fun provideIsFavoriteByIdUseCase() = IsFavoriteByIdUseCase(favoriteRepository)

        override fun provideSaveMovieToFavoriteUseCase() =
            SaveMovieToFavoriteUseCase(favoriteRepository)

        override fun provideRetrofit() = retrofit
    }

    @[Provides AppScope]
    internal fun provideFavoriteRepository(context: Context): FavoriteRepository =
        FavoriteFactory().provideRepository(context)

    @[Provides AppScope]
    internal fun provideContext(application: Application) = application.applicationContext

    @[Provides AppScope]
    internal fun provideBuildConfigWrapper() = object : BuildConfigWrapper {

        override fun movieBaseUrl() = BuildConfig.MOVIE_BASE_URL

        override fun movieApiKey() = BuildConfig.MOVIE_API_KEY

        override fun imageBaseUrl() = BuildConfig.BASE_IMAGE_PATH

        override fun isDebug() = BuildConfig.DEBUG
    }
}