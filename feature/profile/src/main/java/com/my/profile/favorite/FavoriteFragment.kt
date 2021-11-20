package com.my.profile.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.my.profile.databinding.FragmentFavoriteBinding
import com.my.profile.favorite.di.FavoriteFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteFactory().provideViewModelFactory(requireActivity().applicationContext)
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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
        binding.list.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.list.adapter = adapter
    }

    private fun subscribeToObservers() {
        viewModel.content.observe(viewLifecycleOwner) { list ->
            val content = list.map { FavoriteItem(it) }
            adapter.addAll(content)
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}