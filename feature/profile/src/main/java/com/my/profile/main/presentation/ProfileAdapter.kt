package com.my.profile.main.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.my.profile.favorite.FavoriteFragment
import com.my.profile.main.domain.entity.ContentViewType
import com.my.profile.main.domain.entity.ProfileFeature
import com.my.profile.watchlist.presentation.WatchlistFragment

class ProfileAdapter(
    fragment: Fragment,
    private val features: List<ProfileFeature>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = features.size

    override fun createFragment(position: Int): Fragment {
        return when (features[position].contentViewType) {
            ContentViewType.FAVORITE -> FavoriteFragment.newInstance()
            ContentViewType.WATCHLIST -> WatchlistFragment.newInstance()
        }
    }
}