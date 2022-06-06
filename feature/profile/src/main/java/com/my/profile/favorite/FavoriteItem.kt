package com.my.profile.favorite

import android.view.View
import com.my.core.extensions.load
import com.my.favorite.domain.usecase.FavoriteEntity
import com.my.profile.R
import com.my.profile.databinding.ItemSmallBinding
import com.xwray.groupie.viewbinding.BindableItem

class FavoriteItem(
    private val content: FavoriteEntity
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout(): Int = R.layout.item_small

    override fun initializeViewBinding(view: View) = ItemSmallBinding.bind(view)

    override fun bind(viewBinding: ItemSmallBinding, position: Int) {
        viewBinding.imagePreview.load(R.drawable.ic_avatar)
    }
}