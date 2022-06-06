package com.my.movies.feed.item

import android.view.View
import com.my.core.extensions.load
import com.my.movies.R
import com.my.movies.databinding.ItemWithTextBinding
import com.my.movies.domain.Movie
import com.xwray.groupie.viewbinding.BindableItem

class MovieItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemWithTextBinding>() {

    override fun getLayout() = R.layout.item_with_text

    override fun initializeViewBinding(view: View) = ItemWithTextBinding.bind(view)

    override fun bind(viewBinding: ItemWithTextBinding, position: Int) {
        viewBinding.description.text = content.title
        viewBinding.movieRating.rating = content.rating
        viewBinding.content.setOnClickListener {
            onClick.invoke(content)
        }
        viewBinding.imagePreview.load(content.posterPath)
    }
}
