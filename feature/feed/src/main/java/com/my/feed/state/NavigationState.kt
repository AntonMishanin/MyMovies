package com.my.feed.state

sealed class NavigationState {
    data class MovieDetails(val id: String) : NavigationState()
    data class Search(val searchText: String) : NavigationState()
    object None : NavigationState()
}