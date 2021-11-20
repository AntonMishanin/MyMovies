package com.my.profile.favorite

import android.view.View
import com.my.domain.entity.MovieDetails
import com.my.profile.R
import com.my.profile.databinding.ItemSmallBinding
import com.my.resources.extensions.load
import com.xwray.groupie.viewbinding.BindableItem

class FavoriteItem(
    private val content: MovieDetails
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout(): Int = R.layout.item_small

    override fun initializeViewBinding(view: View) = ItemSmallBinding.bind(view)

    override fun bind(viewBinding: ItemSmallBinding, position: Int) {
        viewBinding.imagePreview.load(R.drawable.ic_avatar)
    }
}