package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.navigation.Navigation
import it.willuz.cleandroid.util.*
import kotlinx.coroutines.CoroutineScope

interface QuotesView: Viper.ViperView {
    fun onQuotes(items: List<QuoteUiItem>)
    fun onLoadingUiVisible(visible: Boolean)
    fun onEmptyUiVisible(visible: Boolean)
    fun onRecyclerVisible(visible: Boolean)
}

interface QuotesPresentationLogic: Viper.ViperPresenter {
    fun refreshClicked()
    fun quoteSelected(item: QuoteUiItem)
}

interface QuotesBusinessLogic: Viper.ViperInteractor {
    fun requestLoadNRandomQuotes(n: Int, scope: CoroutineScope)
}

interface QuotesInteractorOutput: Viper.ViperInteractorOutput {
    fun responseLoadNRandomQuotes(items: List<QuoteUiItem>)
    fun onError(message: String)
}

interface QuotesRoutingLogic: Viper.ViperRouter {
    fun showQuoteDetails(quote: QuoteUiItem): Navigation
}