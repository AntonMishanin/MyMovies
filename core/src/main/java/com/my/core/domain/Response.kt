package com.my.core.domain

interface Response<in T : Any> {

    data class Success<T : Any>(
        val value: T
    ) : Response<T>

    data class Error<in T : Any>(
        val sourceException: Throwable,
        val domainException: Exception
    ) : Response<T>
}