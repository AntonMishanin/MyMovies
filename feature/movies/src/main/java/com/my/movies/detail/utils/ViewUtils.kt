package com.my.movies.detail.utils

import android.widget.CheckBox

fun CheckBox.setCheckedListener(isChecked: (Boolean) -> Unit) {
    this.setOnCheckedChangeListener { _, value ->
        isChecked(value)
    }
}