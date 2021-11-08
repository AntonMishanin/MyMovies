package com.my.tv

import com.my.domain.entity.TvShowsEntity
import com.my.domain.repository.TvRepository
import com.my.tv.converter.TvConverter
import com.my.tv.remote.TvRemoteDataSource
import io.reactivex.Single

internal class TvRepositoryImpl(
    private val remote: TvRemoteDataSource
) : TvRepository {
    override fun fetchPopularTvShows(): Single<List<TvShowsEntity>> {
        return remote.fetchPopularTvShows()
            .map { it.results?.map { dto -> TvConverter.toValueObject(dto) } }
    }
}