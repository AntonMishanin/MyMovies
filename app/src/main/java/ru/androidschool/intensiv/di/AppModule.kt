package ru.androidschool.intensiv.di

import android.app.Application
import android.content.Context
import com.my.core.di.AppScope
import com.my.core.di.BuildConfigWrapper
import com.my.core.di.SchedulersWrapper
import com.my.favorite.domain.repository.FavoriteRepository
import com.my.favorite.domain.usecase.DeleteFromFavoriteByIdUseCase
import com.my.favorite.domain.usecase.FetchAllFavoriteUseCase
import com.my.favorite.domain.usecase.IsFavoriteByIdUseCase
import com.my.favorite.domain.usecase.SaveMovieToFavoriteUseCase
import com.my.movies.detail.di.MovieDetailsDependencies
import com.my.movies.feed.di.FeedDependencies
import com.my.profile.di.ProfileDependencies
import com.my.search.di.SearchDependencies
import com.my.tv_shows.di.TvShowsDependencies
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.androidschool.intensiv.BuildConfig

@Module
internal class AppModule {

    @[Provides AppScope]
    fun provideProfileDependencies(
        favoriteRepository: FavoriteRepository,
        fetchAllFavoriteUseCase: FetchAllFavoriteUseCase
    ) = object : ProfileDependencies {

        override fun provideFavoriteRepository() = favoriteRepository

        override fun provideFetchAllFavoriteUseCase() = fetchAllFavoriteUseCase
    }

    @[Provides AppScope]
    fun provideTvShowsDependencies(retrofit: Retrofit) = object : TvShowsDependencies {

        override fun provideRetrofit() = retrofit
    }

    @[Provides AppScope]
    internal fun provideSearchDependencies() = object : SearchDependencies {}

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
        retrofit: Retrofit,
        deleteFromFavoriteByIdUseCase: DeleteFromFavoriteByIdUseCase,
        saveMovieToFavoriteUseCase: SaveMovieToFavoriteUseCase,
        isFavoriteByIdUseCase: IsFavoriteByIdUseCase
    ) = object : MovieDetailsDependencies {

        override fun provideContext() = context

        override fun provideDeleteFromFavoriteByIdUseCase() = deleteFromFavoriteByIdUseCase

        override fun provideIsFavoriteByIdUseCase() = isFavoriteByIdUseCase

        override fun provideSaveMovieToFavoriteUseCase() = saveMovieToFavoriteUseCase

        override fun provideRetrofit() = retrofit
    }

    @[Provides AppScope]
    internal fun provideContext(application: Application) = application.applicationContext

    @[Provides AppScope]
    internal fun provideBuildConfigWrapper() = object : BuildConfigWrapper {

        override fun movieBaseUrl() = BuildConfig.MOVIE_BASE_URL

        override fun movieApiKey() = BuildConfig.MOVIE_API_KEY

        override fun imageBaseUrl() = BuildConfig.BASE_IMAGE_PATH

        override fun isDebug() = BuildConfig.DEBUG
    }

    @[Provides AppScope]
    internal fun provideSchedulersWrapper(): SchedulersWrapper = SchedulersWrapper.Impl()
}