package com.aitor.domestikacodechallenge.common

import android.view.View

fun List<View>.setVisible(visible: Boolean = true) {
    forEach {
        it.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
