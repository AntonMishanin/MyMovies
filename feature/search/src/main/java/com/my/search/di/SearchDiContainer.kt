package com.my.search.di

import androidx.lifecycle.ViewModel

internal class SearchDiContainer : ViewModel() {

    private var component: SearchComponent? = null

    fun component(dependencies: SearchDependencies): SearchComponent {
        if (component == null) {
            component = DaggerSearchComponent.builder().dependencies(dependencies).build()
        }
        return component!!
    }
}