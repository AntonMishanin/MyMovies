package com.my.core.presentation

import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.xwray.groupie.viewbinding.BindableItem

abstract class BaseItem<VB : ViewBinding>(
    @LayoutRes private val layout: Int,
    private val bind: (View) -> VB
) : BindableItem<VB>() {

    final override fun getLayout() = layout

    final override fun initializeViewBinding(view: View): VB = bind(view)
}