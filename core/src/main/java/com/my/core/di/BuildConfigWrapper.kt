package com.my.core.di

interface BuildConfigWrapper {

    fun movieBaseUrl(): String

    fun movieApiKey(): String

    fun imageBaseUrl(): String

    fun isDebug(): Boolean
}