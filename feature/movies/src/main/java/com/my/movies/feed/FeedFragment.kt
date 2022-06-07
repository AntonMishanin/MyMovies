package com.my.movies.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.my.core.di.DependenciesProvider
import com.my.core.extensions.hide
import com.my.movies.R
import com.my.movies.databinding.FragmentFeedBinding
import com.my.movies.domain.Movie
import com.my.movies.feed.di.FeedDependencies
import com.my.movies.feed.di.FeedDiContainer
import com.my.movies.feed.item.MainCardContainer
import com.my.movies.feed.item.MovieItem
import com.my.movies.feed.navigator.FeedNavigator
import com.my.movies.feed.state.NavigationState
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FeedViewModel by lazy {
        viewModels<FeedDiContainer>().value.getComponent(
            (requireActivity().application as DependenciesProvider).provide(FeedDependencies::class)
        ).provideViewModel()
    }

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
        viewModel.content.observe(viewLifecycleOwner) {
            binding.loader.hide()
            adapter.clear()
            handleMovies(it.nowPlaying, titleRes = R.string.recommended)
            handleMovies(it.popular, titleRes = R.string.popular)
            handleMovies(it.upcoming, titleRes = R.string.upcoming)
        }
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigation)
    }

    private fun handleMovies(movies: List<Movie>, titleRes: Int) {
        val content = movies.map { MovieItem(it, viewModel::onMovieItemClicked) }
        val title = requireContext().getString(titleRes)
        val moviesList = listOf(MainCardContainer(title, content))
        adapter.addAll(moviesList)
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
        navigator()?.openMovieDetails(id)
    }

    private fun openSearch(searchText: String) {
        viewModel.onNavigationSuccess()
        navigator()?.openSearch(searchText)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    private fun navigator() = requireActivity() as? FeedNavigator
}