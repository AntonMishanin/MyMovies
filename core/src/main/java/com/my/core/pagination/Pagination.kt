package com.my.core.pagination

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

data class Pagination(
    private val config: PaginationConfig = PaginationConfig(),
    private var state: PaginationState = PaginationState(),
    private val observable: PublishSubject<Pair<Int, Int>> = PublishSubject.create()
) {

    private var paginationCallback: ((page: Int) -> Observable<*>) = { Observable.empty<Unit>() }

    //TODO: think about public property
    var page = 1

    init {
        observable
            .filter { config.isEndReached(it) }
            .throttleFirst(THROTTLE_DURATION_MILLISECONDS, TimeUnit.MILLISECONDS)
            .filter { state.isAvailable() }
            .map {
                state.onStart()
                page = config.nextPage()
                page
            }
            .flatMap { paginationCallback.invoke(it) }
            .doOnNext { state.onFinish() }
            .subscribe()
    }

    fun loadMoreRequest(currentPosition: Int, itemsSize: Int) =
        observable.onNext(Pair(currentPosition, itemsSize))

    fun paginationCallback(paginationCallback: ((page: Int) -> Observable<*>)) {
        this.paginationCallback = paginationCallback
    }

    companion object {
        private const val THROTTLE_DURATION_MILLISECONDS = 1000L
    }
}