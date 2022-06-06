package com.my.movies.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.my.core.extensions.load
import com.my.movies.databinding.FragmentMovieDetailsBinding
import com.my.movies.detail.di.MovieDetailsFactory
import com.my.movies.detail.utils.setCheckedListener
import com.my.movies.domain.MovieDetails
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsFactory()
            .provideViewModelFactory(arguments, requireActivity().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        subscribeObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = null
        binding.bodyContent.isFavorite.setCheckedListener(viewModel::onFavoriteClicked)
    }

    private fun subscribeObservers() {
        viewModel.movie.observe(viewLifecycleOwner, ::handleMovieState)
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            binding.bodyContent.isFavorite.isChecked = it
        }
    }

    private fun handleMovieState(movie: MovieDetails) {
        binding.preview.load(movie.posterPath)
        with(binding.bodyContent) {
            bodyTitle.text = movie.title
            description.text = movie.overview
            rating.rating = movie.rating
            studioValue.text = movie.studio
            genreValue.text = movie.genre
            yearValue.text = movie.year
            actors.adapter = adapter
        }

        // val list = movie.actorsList.map { ActorItem(it, viewModel::onItemActorClicked) }
        // adapter.apply { addAll(list) }
    }
}
