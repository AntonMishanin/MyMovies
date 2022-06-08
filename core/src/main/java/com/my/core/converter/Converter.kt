package com.my.core.converter

interface Converter<in I : Any, out R : Any> {

    fun convert(input: I): R
}