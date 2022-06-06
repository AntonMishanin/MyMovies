package com.my.core.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxPresenter<T : BaseView> {

    var view: T? = null

    private val compositeDisposable = CompositeDisposable()

    fun onViewAttached(view: T) {
        this.view = view
        onViewReady()
    }

    open fun onViewDetached() {
        view = null
        compositeDisposable.dispose()
    }

    abstract fun onViewReady()

    protected fun Disposable.addToComposite() = compositeDisposable.add(this)
}