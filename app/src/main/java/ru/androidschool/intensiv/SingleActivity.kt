package ru.androidschool.intensiv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.my.core.KEY_ID
import com.my.core.KEY_SEARCH
import com.my.movies.feed.navigator.FeedNavigator

class SingleActivity : AppCompatActivity(R.layout.activity_single), FeedNavigator {

    private lateinit var navController: NavController

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val host =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = host.navController

        setupBottomNavMenu(navController)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav?.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    /**
     * Handle navigation from feature Feed
     */

    override fun openMovieDetails(id: String) {
        val bundle = Bundle()
        bundle.putString(KEY_ID, id)
        navController.navigate(R.id.movie_details_fragment, bundle, options)
    }

    override fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        navController.navigate(R.id.search_dest, bundle, options)
    }
}
