package com.my.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.my.movie.di.MoviesFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.my.domain.entity.Movie
import com.my.feed.databinding.FragmentFeedBinding
import io.reactivex.disposables.CompositeDisposable
import ru.androidschool.intensiv.ui.afterTextChanged

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val compositeDisposable = CompositeDisposable()

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

        binding.feedHeader.searchToolbar.editText.afterTextChanged {
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        binding.moviesRecyclerView.adapter = adapter

        val remote = MoviesFactory().provideMovieRepository()
        compositeDisposable.add(remote.fetchNowPlaying().subscribe(::handleNowPlayingMovies))
        compositeDisposable.add(remote.fetchPopular().subscribe(::handlePopularMovies))
        compositeDisposable.add(remote.fetchUpcoming().subscribe(::handleUpcomingMovies))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleNowPlayingMovies(movies: List<Movie>?) {
        if (movies.isNullOrEmpty()) return

        val content = movies.map { MovieItem(it) { movie -> openMovieDetails(movie) } }.toList()
        val nowPlayingMoviesList = listOf(MainCardContainer(R.string.recommended, content))
        adapter.apply { addAll(nowPlayingMoviesList) }
    }

    private fun handlePopularMovies(movies: List<Movie>?) {
        if (movies.isNullOrEmpty()) return

        val content = movies.map { MovieItem(it) { movie -> openMovieDetails(movie) } }.toList()
        val popularMoviesList = listOf(MainCardContainer(R.string.popular, content))
        adapter.apply { addAll(popularMoviesList) }
    }

    private fun handleUpcomingMovies(movies: List<Movie>?) {
        if (movies.isNullOrEmpty()) return

        val content = movies.map { MovieItem(it) { movie -> openMovieDetails(movie) } }.toList()
        val upcomingMoviesList = listOf(MainCardContainer(R.string.upcoming, content))
        adapter.apply { addAll(upcomingMoviesList) }
    }

    private fun openMovieDetails(movie: Movie) {
        (requireActivity() as? FeedNavigator)?.openMovieDetails(movie.title)
    }

    private fun openSearch(searchText: String) {
        (requireActivity() as? FeedNavigator)?.openSearch(searchText)
    }

    override fun onStop() {
        super.onStop()
        binding.feedHeader.searchToolbar.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {
        const val MIN_LENGTH = 3
    }
}
