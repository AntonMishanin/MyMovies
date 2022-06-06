package com.my.tv_shows.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.my.tv_shows.databinding.FragmentTvShowsBinding
import com.my.tv_shows.di.TvShowsFactory
import com.my.tv_shows.domain.TvShowsEntity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class TvShowsFragment : Fragment(), TvShowsView {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()
    private val presenter by lazy { TvShowsFactory().providePresenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewAttached(view = this)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetached()
        _binding = null
    }

    private fun initView() {
        binding.tvShowsRecyclerView.adapter = adapter
    }

    override fun setTvShowsList(content: List<TvShowsEntity>) {
        val list = content.map { TvShowsItem(it, presenter::onItemTvShowClicked) }
        adapter.apply { addAll(list) }
    }
}
