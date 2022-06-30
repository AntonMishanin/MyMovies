package com.my.tv_shows.data

import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

internal class MemoryCacheDataSource(
    private val observable: BehaviorSubject<List<TvShowsEntity>> = BehaviorSubject.create(),
    private val data: MutableList<TvShowsEntity> = mutableListOf()
) {

    fun read(): Single<List<TvShowsEntity>> = Single.just(data)

    fun replace(data: List<TvShowsEntity>) {
        this.data.clear()
        add(data)
    }

    fun add(data: List<TvShowsEntity>) {
        this.data.addAll(data)
        observable.onNext(this.data)
    }

    fun observable(): Flowable<List<TvShowsEntity>> =
        observable.toFlowable(BackpressureStrategy.LATEST)
}