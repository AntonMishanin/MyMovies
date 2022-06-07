package com.my.core.presentation

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel {

    private val compositeDisposable = CompositeDisposable()

    protected open fun finalize() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.addToComposite() = compositeDisposable.add(this)
}