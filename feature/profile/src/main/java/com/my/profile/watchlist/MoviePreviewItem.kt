package com.my.profile.watchlist

import android.view.View
import com.my.core.extensions.load
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.profile.R
import com.my.profile.databinding.ItemSmallBinding
import com.xwray.groupie.viewbinding.BindableItem

class MoviePreviewItem(
    private val content: FavoriteEntity,
    private val onClick: (FavoriteEntity) -> Unit
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
