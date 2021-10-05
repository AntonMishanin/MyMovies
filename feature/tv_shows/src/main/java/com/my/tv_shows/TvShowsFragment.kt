package com.my.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class TvShowsFragment : Fragment(R.layout.fragment_tv_shows) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val content = fetchList().map { TvShowItem(it, ::onItemTvShowClicked) }

        val adapter = GroupAdapter<GroupieViewHolder>()
        adapter.apply { addAll(content) }

        val tvShowsRecyclerView = view?.findViewById<RecyclerView>(R.id.tv_shows_recycler_view)
        tvShowsRecyclerView?.adapter = adapter
    }

    private fun fetchList() = listOf(
        TvShowEntity(id = "", imagePath = R.drawable.preview, title = "Title1", rating = 3f),
        TvShowEntity(id = "", imagePath = R.drawable.preview, title = "Title2", rating = 3f),
        TvShowEntity(id = "", imagePath = R.drawable.preview, title = "Title3", rating = 3f),
        TvShowEntity(id = "", imagePath = R.drawable.preview, title = "Title4", rating = 3f)
    )

    private fun onItemTvShowClicked(tvShowEntity: TvShowEntity) {

    }
}
