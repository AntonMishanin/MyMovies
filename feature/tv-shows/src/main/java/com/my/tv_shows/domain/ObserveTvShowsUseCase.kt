package com.my.tv_shows.domain

internal class ObserveTvShowsUseCase(
    private val repository: TvRepository
) {

    fun invoke() = repository.observeTvShows()
}