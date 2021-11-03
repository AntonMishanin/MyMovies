package ru.androidschool.intensiv.ui.watchlist

import android.view.View
import com.my.domain.entity.Movie
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.ItemSmallBinding

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
        // TODO Получать из модели
        Picasso.get()
            .load("https://www.kinopoisk.ru/images/film_big/1143242.jpg")
            .into(viewBinding.imagePreview)
    }
}
