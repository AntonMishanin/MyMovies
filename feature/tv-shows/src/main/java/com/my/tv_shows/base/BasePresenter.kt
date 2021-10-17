package com.my.tv_shows.base

abstract class BasePresenter<T : BaseView> {

    var view: T? = null

    fun onViewAttached(view: T) {
        this.view = view
        onViewReady()
    }

    fun onViewDetached() {
        view = null
    }

    abstract fun onViewReady()
}