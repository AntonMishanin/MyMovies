package com.my.tv_shows.data

import com.my.tv_shows.domain.TvShowsEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

internal class MemoryCacheDataSource(
    private val observable: BehaviorSubject<List<TvShowsEntity>> = BehaviorSubject.create()
) {

    fun read(): Single<List<TvShowsEntity>> = Single.just(observable.value)

    fun save(data: List<TvShowsEntity>) {
        this.observable.onNext(data)
    }

    fun observable(): Flowable<List<TvShowsEntity>> =
        observable.toFlowable(BackpressureStrategy.LATEST)
}