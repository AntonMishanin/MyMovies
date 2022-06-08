package ru.androidschool.intensiv.di

import android.app.Application
import com.my.core.data.RetrofitModule
import com.my.core.di.AppScope
import com.my.favorite.FavoriteCommonModule
import com.my.movies.detail.di.MovieDetailsDependencies
import com.my.movies.feed.di.FeedDependencies
import com.my.profile.di.ProfileDependencies
import com.my.search.di.SearchDependencies
import com.my.tv_shows.presentation.di.TvShowsDependencies
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(
    modules = [AppModule::class, RetrofitModule::class, FavoriteCommonModule::class]
)]
internal interface AppComponent {

    fun provideProfileDependencies(): ProfileDependencies

    fun provideTvShowsDependencies(): TvShowsDependencies

    fun provideSearchDependencies(): SearchDependencies

    fun provideFeedDependencies(): FeedDependencies

    fun provideMovieDetailsDependencies(): MovieDetailsDependencies

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}