package ru.androidschool.intensiv

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.my.bottom_navigation.BottomNavigation
import com.my.core.KEY_ID
import com.my.core.KEY_SEARCH
import com.my.movies.feed.navigator.FeedNavigation

open class MainNavigation : FeedNavigation, BottomNavigation {

    private var bottomNavigationController: NavController? = null

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    fun onSupportNavigateUp(): Boolean? {
        return bottomNavigationController?.navigateUp()
    }

    /**
     * Handle navigation from feature Feed
     */

    override fun openMovieDetails(id: String) {
        val bundle = Bundle()
        bundle.putString(KEY_ID, id)
        bottomNavigationController?.navigate(R.id.movie_details_fragment, bundle, options)
    }

    override fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        bottomNavigationController?.navigate(R.id.search_dest, bundle, options)
    }

    /**
     * Handle navigation from feature bottom navigation
     */

    override fun init(navController: NavController) {
        bottomNavigationController = navController
    }
}