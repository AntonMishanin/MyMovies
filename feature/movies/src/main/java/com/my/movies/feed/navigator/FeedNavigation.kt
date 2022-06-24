package com.my.movies.feed.navigator

import com.my.core.navigation.Navigation

interface FeedNavigation : Navigation {

    fun openMovieDetails(id: String)

    fun openSearch(searchText: String)
}