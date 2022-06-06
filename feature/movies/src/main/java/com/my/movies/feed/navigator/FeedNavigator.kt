package com.my.movies.feed.navigator

interface FeedNavigator {

    fun openMovieDetails(id: String)

    fun openSearch(searchText: String)
}