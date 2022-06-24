package ru.androidschool.intensiv

import androidx.appcompat.app.AppCompatActivity
import com.my.bottom_navigation.BottomNavigation
import com.my.core.navigation.Navigation
import com.my.core.navigation.ProvideNavigation
import com.my.movies.feed.navigator.FeedNavigation

class SingleActivity : AppCompatActivity(R.layout.activity_single), ProvideNavigation {

    private val mainNavigator = MainNavigation()

    override fun onSupportNavigateUp(): Boolean {
        return mainNavigator.onSupportNavigateUp() ?: super.onSupportNavigateUp()
    }

    override fun <T : Navigation> provideNavigation(clazz: Class<T>): T {
        return when (clazz) {
            FeedNavigation::class.java -> mainNavigator
            BottomNavigation::class.java -> mainNavigator
            else -> throw IllegalArgumentException("Unknown class $clazz")
        } as T
    }
}
