package com.my.core.di

import kotlin.reflect.KClass

interface DependenciesProvider {

    fun <T : FeatureDependencies> provide(kClass: KClass<T>): T
}