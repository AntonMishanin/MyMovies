package com.my.movies.detail.di

import android.os.Bundle
import androidx.lifecycle.ViewModel

internal class MovieDetailsDiContainer : ViewModel() {

    private var component: MovieDetailsComponent? = null

    fun getComponent(
        dependencies: MovieDetailsDependencies,
        arguments: Bundle?
    ): MovieDetailsComponent {
        if (component == null) {
            component = DaggerMovieDetailsComponent
                .builder()
                .dependencies(dependencies)
                .arguments(arguments)
                .build()
        }
        return component!!
    }
}