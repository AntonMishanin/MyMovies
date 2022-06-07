package com.my.search.di

import com.my.search.SearchViewModel
import dagger.Component

@Component(
    modules = [SearchModule::class],
    dependencies = [SearchDependencies::class]
)
internal interface SearchComponent {

    fun provideSearchViewModel(): SearchViewModel

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: SearchDependencies): Builder

        fun build(): SearchComponent
    }
}