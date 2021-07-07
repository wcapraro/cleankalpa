package it.willuz.cleandroid.navigation

import android.content.Context
import android.content.Intent

object NavigationKeys {
    const val toQuoteDetails_KeyQuoteId = "quote_id"
}

abstract class Navigation(val to: Class<*>?, val params: NavigationParams?)

data class NavigationParams(val params: Map<String, String>)

fun Navigation.toIntent(from: Context): Intent? {
    this.to ?: return null
    return Intent(from, this.to).also {
        this.params?.params?.let { p ->
            p.forEach { (t, u) -> it.putExtra(t, u) }
        }
    }
}