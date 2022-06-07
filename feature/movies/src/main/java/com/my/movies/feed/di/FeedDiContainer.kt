package com.my.movies.feed.di

import androidx.lifecycle.ViewModel

internal class FeedDiContainer : ViewModel() {

    private var component: FeedComponent? = null

    fun getComponent(dependencies: FeedDependencies): FeedComponent {
        if (component == null) {
            component = DaggerFeedComponent.builder().dependencies(dependencies).build()
        }
        return component!!
    }
}