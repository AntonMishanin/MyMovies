package com.my.movies.feed.di

import com.my.movies.data.di.MoviesDataModule
import com.my.movies.feed.FeedViewModel
import dagger.Component

@Component(
    modules = [FeedModule::class, MoviesDataModule::class],
    dependencies = [FeedDependencies::class]
)
interface FeedComponent {

    fun provideViewModel(): FeedViewModel

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: FeedDependencies): Builder

        fun build(): FeedComponent
    }
}