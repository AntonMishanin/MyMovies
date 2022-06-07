package com.my.core.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import java.util.concurrent.TimeUnit

interface SchedulersWrapper {

    fun ui(): Scheduler

    fun single(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

    fun trampoline(): Scheduler

    fun newThread(): Scheduler

    fun test(delayTime: Long = 0, unit: TimeUnit = TimeUnit.SECONDS): Scheduler

    class Impl : SchedulersWrapper {

        override fun ui(): Scheduler = AndroidSchedulers.mainThread()

        override fun single() = Schedulers.single()

        override fun computation() = Schedulers.computation()

        override fun io() = Schedulers.io()

        override fun trampoline() = Schedulers.trampoline()

        override fun newThread() = Schedulers.newThread()

        override fun test(delayTime: Long, unit: TimeUnit) = TestScheduler()
    }
}