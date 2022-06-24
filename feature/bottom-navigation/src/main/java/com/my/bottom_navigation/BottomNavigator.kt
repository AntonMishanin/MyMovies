package com.my.bottom_navigation

import androidx.navigation.NavController

interface BottomNavigator {

    fun init(navController: NavController)

    fun navigateToHome()

    fun navigateToTvShows()

    fun navigateToProfile()
}