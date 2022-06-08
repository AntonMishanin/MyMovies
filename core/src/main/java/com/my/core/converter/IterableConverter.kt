package com.my.core.converter

abstract class IterableConverter<in I : Any, out R : Any> : Converter<I, R> {

    fun convert(iterable: Iterable<I>): List<R> = iterable.map { convert(it) }
}