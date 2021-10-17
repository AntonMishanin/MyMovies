package com.my.movie_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.my.movie_details.databinding.FragmentMovieDetailsBinding
import com.my.movie_details.di.MovieDetailsFactory
import com.my.movie_details.entity.MovieEntity
import com.my.movie_details.utils.setCheckedListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsFactory().provideViewModelFactory(arguments)
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
    }

    private fun subscribeObservers() {
        viewModel.movie.observe(viewLifecycleOwner, ::handleMovieState)
    }

    private fun handleMovieState(movie: MovieEntity) {
        Picasso.get().load(movie.previewId).into(binding.preview)
        with(binding.bodyContent) {
            bodyTitle.text = movie.title
            isFavorite.setCheckedListener(viewModel::onFavoriteClicked)
            isFavorite.isSelected = movie.isFavorite
            description.text = movie.descriptionFull
            rating.rating = movie.rating
            studioValue.text = movie.studio
            genreValue.text = movie.genre
            yearValue.text = movie.year
            actors.adapter = adapter
        }

        val list = movie.actorsList.map { ActorItem(it, viewModel::onItemActorClicked) }
        adapter.apply { addAll(list) }
    }
}
