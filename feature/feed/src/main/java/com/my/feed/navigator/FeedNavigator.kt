package com.my.feed.navigator

interface FeedNavigator {

    fun openMovieDetails(id: String)

    fun openSearch(searchText: String)
}