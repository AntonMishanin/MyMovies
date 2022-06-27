package com.my.tv_shows.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.my.core.di.DependenciesProvider
import com.my.tv_shows.databinding.FragmentTvShowsBinding
import com.my.tv_shows.di.TvShowsDependencies
import com.my.tv_shows.di.TvShowsDiContainer
import com.my.tv_shows.presentation.TvShowsView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

class TvShowsFragment : Fragment(), TvShowsView {

    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()

    private val presenter by lazy {
        viewModels<TvShowsDiContainer>().value.component(
            (requireActivity().application as DependenciesProvider).provide(TvShowsDependencies::class)
        ).providePresenter()
    }

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
        binding.tvShowsRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onViewDetached()
        _binding = null
    }

    override fun showState(state: List<BindableItem<*>>) {
        adapter.replaceAll(state)
    }
}
