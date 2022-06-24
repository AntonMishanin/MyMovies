package com.my.bottom_navigation

import androidx.navigation.NavController
import com.my.core.navigation.Navigation

interface BottomNavigation : Navigation {

    fun init(navController: NavController)
}