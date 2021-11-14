package com.my.search

import android.text.Editable
import com.my.resources.mvvm.RxViewModel
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchViewModel : RxViewModel() {

    private val searchSubject = PublishSubject.create<String>()
    private val mockContent = listOf(
        "test",
        "testhj",
        "testh",
        "test"
    )

    init {
        addTimeoutForSearch()
    }

    private fun addTimeoutForSearch() {
        searchSubject
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                Timber.d(it)
                filterContentByText(it)
            }.addToComposite()
    }

    fun onSearchTextChanged(text: Editable?) {
        searchSubject.onNext(text.toString())
    }

    private fun filterContentByText(searchText: String) {
        Flowable.fromIterable(mockContent)
            .filter {
                it.contains(searchText)
            }
            .toList()
            .subscribe { list ->
                Timber.d("${list.size}")
            }.addToComposite()
    }
}