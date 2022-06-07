package com.my.tv_shows.presentation.di

import androidx.lifecycle.ViewModel

internal class TvShowsDiContainer : ViewModel() {

    private var component: TvShowsComponent? = null

    fun component(dependencies: TvShowsDependencies): TvShowsComponent {
        if (component == null) {
            component = DaggerTvShowsComponent.builder().dependencies(dependencies).build()
        }
        return component!!
    }
}