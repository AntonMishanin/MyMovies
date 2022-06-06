package com.my.profile.watchlist

import android.view.View
import com.my.domain.entity.Movie
import com.my.profile.R
import com.my.profile.databinding.ItemSmallBinding
import com.my.resources.extensions.load
import com.xwray.groupie.viewbinding.BindableItem

class MoviePreviewItem(
    private val content: Movie,
    private val onClick: (Movie) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout() = R.layout.item_small

    override fun initializeViewBinding(view: View) = ItemSmallBinding.bind(view)

    override fun bind(viewBinding: ItemSmallBinding, position: Int) {
        viewBinding.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }

        viewBinding.imagePreview.load(content.posterPath)
    }
}
