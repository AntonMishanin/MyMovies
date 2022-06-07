package com.my.movies.detail.di

import android.os.Bundle
import com.my.core.di.Feature
import com.my.movies.data.di.MoviesDataModule
import com.my.movies.detail.presentation.MovieDetailsViewModel
import dagger.BindsInstance
import dagger.Component

@Feature
@Component(
    modules = [MovieDetailsModule::class, MoviesDataModule::class],
    dependencies = [MovieDetailsDependencies::class]
)
internal interface MovieDetailsComponent {

    fun provideViewModel(): MovieDetailsViewModel

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: MovieDetailsDependencies): Builder

        @BindsInstance
        fun arguments(arguments: Bundle?): Builder

        fun build(): MovieDetailsComponent
    }
}