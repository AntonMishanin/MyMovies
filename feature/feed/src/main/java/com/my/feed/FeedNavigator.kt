package com.my.feed

interface FeedNavigator {

    fun openMovieDetails(id: String)

    fun openSearch(searchText: String)
}