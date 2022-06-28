package com.my.tv_shows.data

import com.my.core.converter.Converter
import com.my.core.domain.NoInternetConnectionException
import com.my.core.domain.UnknownException
import java.net.UnknownHostException

internal class ToDomainExceptionConverter : Converter<Throwable, Exception> {

    override fun convert(input: Throwable): Exception {
        return when (input) {
            is UnknownHostException -> NoInternetConnectionException()
            else -> UnknownException()
        }
    }
}