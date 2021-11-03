package com.my.feed.item

import android.view.View
import com.my.domain.entity.Movie
import com.my.feed.R
import com.my.feed.databinding.ItemWithTextBinding
import com.my.resources.extensions.load
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
