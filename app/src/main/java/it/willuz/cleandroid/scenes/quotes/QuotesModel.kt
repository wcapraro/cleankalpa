package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.entity.Author

data class QuotesViewState(val isLoading: Boolean = false, val emptyUiVisible: Boolean = true) {
    fun loading(l: Boolean) = copy(isLoading = l)
    fun empty(e: Boolean) = copy(emptyUiVisible = e)
}

sealed class QuotesEvents {
    object Noop: QuotesEvents()
    data class QuoteDetail(val quoteId: Int): QuotesEvents()
}

data class QuoteUiItem(val id: Int, val quote: String, val author: String)

fun Author.displayName(): String {
    return "$firstName $lastName"
}