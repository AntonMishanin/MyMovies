package com.my.tv_shows.data

import com.my.tv_shows.data.converter.TvConverter
import com.my.tv_shows.data.remote.TvRemoteDataSource
import com.my.tv_shows.domain.TvRepository
import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.Single

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource
) : TvRepository {
    override fun fetchPopularTvShows(): Single<List<TvShowsEntity>> {
        return remote.fetchPopularTvShows()
            .map { it.results?.map { dto -> TvConverter.toValueObject(dto) } }
    }
}