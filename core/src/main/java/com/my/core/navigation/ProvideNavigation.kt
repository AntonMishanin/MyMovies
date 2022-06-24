package com.my.core.navigation

interface ProvideNavigation {

    fun <T : Navigation> provideNavigation(clazz: Class<T>): T
}