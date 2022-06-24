package com.my.core.data

import com.my.core.converter.Converter
import com.my.core.domain.NoInternetConnectionException
import com.my.core.domain.Response
import com.my.core.domain.UnknownException
import java.net.UnknownHostException

class HandleResponse<R : Any> {

    fun <I : Any> handleResult(input: I, converter: Converter<I, R>): Response<R> {
        return try {
            val result = converter.convert(input)
            Response.Success(result)
        } catch (e: Exception) {
            handleResult(e)
        }
    }

    fun handleResult(throwable: Throwable): Response<R> {
        val exception = when (throwable) {
            is UnknownHostException -> NoInternetConnectionException()
            else -> UnknownException()
        }
        return Response.Error(
            sourceException = throwable,
            domainException = exception
        )
    }
}