package com.my.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.my.domain.entity.Movie
import com.my.feed.databinding.FragmentFeedBinding
import com.my.feed.di.FeedFactory
import com.my.feed.item.MainCardContainer
import com.my.feed.item.MovieItem
import com.my.feed.navigator.FeedNavigator
import com.my.feed.state.NavigationState
import com.my.resources.extensions.hide
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FeedViewModel by viewModels { FeedFactory().provideViewModelFactory() }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeObservers()
    }

    override fun onStop() {
        super.onStop()
        binding.feedHeader.searchToolbar.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        binding.moviesRecyclerView.adapter = adapter

        binding.feedHeader.searchToolbar.editText.afterTextChanged {
            viewModel.onSearchTextChanged(it)
        }
    }

    private fun subscribeObservers() {
        viewModel.nowPlaying.observe(viewLifecycleOwner) {
            binding.loader.hide()
            handleMovies(it, titleRes = R.string.recommended)
        }
        viewModel.popular.observe(viewLifecycleOwner) {
            handleMovies(it, titleRes = R.string.popular)
        }
        viewModel.upcoming.observe(viewLifecycleOwner) {
            handleMovies(it, titleRes = R.string.upcoming)
        }
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigation)
    }

    private fun handleMovies(movies: List<Movie>, titleRes: Int) {
        val content = movies.map { MovieItem(it, viewModel::onMovieItemClicked) }.toList()
        val title = requireContext().getString(titleRes)
        val moviesList = listOf(MainCardContainer(title, content))
        adapter.apply { addAll(moviesList) }
    }

    private fun handleNavigation(state: NavigationState) {
        when (state) {
            is NavigationState.MovieDetails -> openMovieDetails(state.id)
            is NavigationState.Search -> openSearch(state.searchText)
            NavigationState.None -> Timber.d("Unused navigation state")
        }
    }

    private fun openMovieDetails(id: String) {
        viewModel.onNavigationSuccess()
        (requireActivity() as? FeedNavigator)?.openMovieDetails(id)
    }

    private fun openSearch(searchText: String) {
        viewModel.onNavigationSuccess()
        (requireActivity() as? FeedNavigator)?.openSearch(searchText)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }
}