package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.navigation.Navigation
import it.willuz.cleandroid.navigation.NavigationKeys
import it.willuz.cleandroid.navigation.NavigationParams
import it.willuz.cleandroid.scenes.detail.QuoteDetailsActivity
import it.willuz.cleandroid.util.BaseViperRouter

class QuotesRouter: BaseViperRouter(), QuotesRoutingLogic {

    override fun showQuoteDetails(quote: QuoteUiItem): Navigation {
        return ToQuoteDetailsNavigation(quote.id)
    }
}

class ToQuoteDetailsNavigation(quoteId: Int): Navigation(QuoteDetailsActivity::class.java,
    NavigationParams(mapOf(Pair(
        NavigationKeys.toQuoteDetails_KeyQuoteId,
        quoteId.toString()))))