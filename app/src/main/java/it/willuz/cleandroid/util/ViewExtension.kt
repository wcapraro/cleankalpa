package it.willuz.cleandroid.util

import android.view.View

fun View.visible(v: Boolean, invisibleAsGone: Boolean = false) {
    this.visibility = if (v) View.VISIBLE else if (invisibleAsGone) View.GONE else View.INVISIBLE
}