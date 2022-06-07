package com.my.profile.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.my.core.di.DependenciesProvider
import com.my.profile.databinding.FragmentWatchlistBinding
import com.my.profile.di.ProfileDependencies
import com.my.profile.di.ProfileDiContainer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

private const val WATCHLIST_SPAN_COUNT = 4

class WatchlistFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        viewModels<ProfileDiContainer>().value.getComponent(
            (requireActivity().application as DependenciesProvider).provide(ProfileDependencies::class)
        ).provideWatchlistViewModel()
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeToObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        binding.moviesRecyclerView.layoutManager = GridLayoutManager(context, WATCHLIST_SPAN_COUNT)
        binding.moviesRecyclerView.adapter = adapter
    }

    private fun subscribeToObservers() {
        viewModel.content.observe(viewLifecycleOwner) { movies ->
            val items = movies.map { MoviePreviewItem(it, viewModel::onMovieItemClicked) }
            adapter.addAll(items)
        }
    }

    companion object {
        fun newInstance() = WatchlistFragment()
    }
}
