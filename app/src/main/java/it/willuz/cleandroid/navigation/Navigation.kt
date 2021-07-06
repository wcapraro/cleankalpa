package it.willuz.cleandroid.navigation

import android.content.Context
import android.content.Intent
import it.willuz.cleandroid.scenes.detail.QuoteDetailsActivity

object NavigationKeys {
    const val toQuoteDetails_KeyQuoteId = "quote_id"
}

sealed class Navigation(val to: Class<*>?, val params: NavigationParams?) {
    object None: Navigation(null, null)
    class ToQuoteDetailsNavigation(quoteId: Int): Navigation(QuoteDetailsActivity::class.java,
        NavigationParams(mapOf(Pair(NavigationKeys.toQuoteDetails_KeyQuoteId,
            quoteId.toString()))))
}

data class NavigationParams(val params: Map<String, String>)

fun Navigation.toIntent(from: Context): Intent? {
    this.to ?: return null
    return Intent(from, this.to).also {
        this.params?.params?.let { p ->
            p.forEach { (t, u) -> it.putExtra(t, u) }
        }
    }
}