package com.my.profile.watchlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.core.di.DependenciesProvider
import com.my.profile.databinding.FragmentWatchlistBinding
import com.my.profile.di.ProfileDependencies
import com.my.profile.di.ProfileDiContainer
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

private const val WATCHLIST_SPAN_COUNT = 4

internal class WatchlistFragment : Fragment() {

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
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.moviesRecyclerView.adapter = adapter
    }

    private fun subscribeToObservers() {
        viewModel.content.observe(viewLifecycleOwner) {
            // Think about different span count for different items
            adapter.replaceAll(it)
        }
    }

    companion object {
        fun newInstance() = WatchlistFragment()
    }
}
