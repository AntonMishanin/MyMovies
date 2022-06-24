package com.my.tv_shows.di

import com.my.tv_shows.presentation.TvShowsPresenter
import dagger.Component

@Component(
    modules = [TvShowsModule::class],
    dependencies = [TvShowsDependencies::class]
)
interface TvShowsComponent {

    fun providePresenter(): TvShowsPresenter

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: TvShowsDependencies): Builder

        fun build(): TvShowsComponent
    }
}