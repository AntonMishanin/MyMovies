package com.my.tv_shows.di

import com.my.core.di.Feature
import com.my.tv_shows.data.di.TvShowsDataModule
import com.my.tv_shows.presentation.TvShowsPresenter
import dagger.Component

@Feature
@Component(
    modules = [TvShowsModule::class, TvShowsDataModule::class],
    dependencies = [TvShowsDependencies::class]
)
internal interface TvShowsComponent {

    fun providePresenter(): TvShowsPresenter

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: TvShowsDependencies): Builder

        fun build(): TvShowsComponent
    }
}