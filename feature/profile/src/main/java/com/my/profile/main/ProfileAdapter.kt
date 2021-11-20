package com.my.profile.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.my.profile.favorite.FavoriteFragment
import com.my.profile.watchlist.WatchlistFragment

private const val FAVORITE = 0
private const val WATCHLIST = 1

class ProfileAdapter(
    fragment: Fragment,
    private val itemsCount: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FAVORITE -> FavoriteFragment.newInstance()
            WATCHLIST -> WatchlistFragment.newInstance()
            else -> throw IllegalArgumentException("UnKnow position $position")
        }
    }
}
