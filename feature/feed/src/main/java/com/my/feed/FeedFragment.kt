package com.my.feed

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.my.domain.entity.Movie
import com.my.feed.databinding.FragmentFeedBinding
import com.my.feed.di.FeedComponentViewModel
import com.my.feed.di.FeedViewModelFactory
import com.my.feed.item.MainCardContainer
import com.my.feed.item.MovieItem
import com.my.feed.navigator.FeedNavigator
import com.my.feed.state.NavigationState
import com.my.resources.extensions.hide
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.Lazy
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber
import javax.inject.Inject

class FeedFragment : Fragment() {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var feedViewModelFactory: Lazy<FeedViewModelFactory>

    private val viewModel: FeedViewModel by viewModels {
        feedViewModelFactory.get()
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FeedComponentViewModel>().component.inject(fragment = this)
        super.onAttach(context)
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