package com.my.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.my.movie_details.databinding.FragmentMovieDetailsBinding
import com.my.movie_details.entity.MovieEntity
import com.my.movie_details.utils.setCheckedListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

private const val KEY_ID = "id"

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter = GroupAdapter<GroupieViewHolder>()

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

        val id = arguments?.getString(KEY_ID) ?: return
        val movie = getMovieById(id)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = null

        Picasso.get().load(movie.previewId).into(binding.preview)
        with(binding.bodyContent) {
            bodyTitle.text = movie.title
            isFavorite.setCheckedListener(::onFavoriteChecked)
            isFavorite.isSelected = movie.isFavorite
            description.text = movie.descriptionFull
            rating.rating = movie.rating
            studioValue.text = movie.studio
            genreValue.text = movie.genre
            yearValue.text = movie.year
            actors.adapter = adapter
        }

        val list = movie.actorsList.map { ActorItem(it, ::onItemActorClicked) }
        adapter.apply { addAll(list) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMovieById(id: String) = MovieEntity(
        id = "",
        previewId = R.drawable.aquaman_preview,
        title = "Movie title",
        rating = 4.3f,
        isFavorite = false,
        descriptionFull = "Deeeeeeeeeeeeeeeeescription full",
        actorsList = fetchActorsByMovie(),
        studio = "Warners",
        genre = "Genre",
        year = "1957"
    )

    private fun fetchActorsByMovie() = listOf(
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        ),
        MovieEntity.Actor(
            id = "",
            name = "sdfsd sdfsd",
            previewId = R.drawable.aquaman_preview
        )
    )

    private fun onFavoriteChecked(isChecked: Boolean) {
    }

    private fun onItemActorClicked(actor: MovieEntity.Actor) {
    }
}
