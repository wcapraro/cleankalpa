package it.willuz.cleandroid.scenes.quotes

import android.app.Activity
import android.content.Intent
import it.willuz.cleandroid.scenes.detail.QuoteDetailsActivity
import it.willuz.cleandroid.util.BaseViperRouter
import java.lang.ref.WeakReference

class QuotesRouter(screen: WeakReference<Activity>): BaseViperRouter(screen), QuotesRoutingLogic {
    companion object {
        const val intentExtraQuoteId = "quote_id"
    }
    override fun showQuoteDetails(quote: QuoteUiItem) {
        Intent(screen.get() ?: return, QuoteDetailsActivity::class.java)
            .apply {
                putExtra(intentExtraQuoteId, quote.id)
            }.also {
                screen.get()?.startActivity(it)
            }
    }
}