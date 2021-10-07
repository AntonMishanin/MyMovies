package com.my.tv_shows.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.my.tv_shows.R
import com.my.tv_shows.di.TvShowsFactory
import com.my.tv_shows.entity.TvShowsEntity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class TvShowsFragment : Fragment(R.layout.fragment_tv_shows), TvShowsView {

    private val adapter = GroupAdapter<GroupieViewHolder>()
    private val presenter by lazy { TvShowsFactory().providePresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAttached(view = this)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetached()
    }

    private fun initView() {
        val tvShowsRecyclerView = view?.findViewById<RecyclerView>(R.id.tv_shows_recycler_view)
        tvShowsRecyclerView?.adapter = adapter
    }

    override fun setTvShowsList(content: List<TvShowsEntity>) {
        val list = content.map { TvShowsItem(it, presenter::onItemTvShowClicked) }
        adapter.apply { addAll(list) }
    }
}
