package it.willuz.cleandroid.scenes

import it.willuz.cleandroid.entity.Author
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.util.quoted

data class QuotesViewState(val isLoading: Boolean = false, val emptyUiVisible: Boolean = true) {
    fun loading(l: Boolean) = copy(isLoading = l)
    fun empty(e: Boolean) = copy(emptyUiVisible = e)
}

sealed class QuotesEvents {
    object Noop: QuotesEvents()
}

data class QuoteUiItem(val id: Int, val authorId: Int, val quote: String, val author: String) {
    companion object {
        fun parse(author: Author?, quote: Quote?): QuoteUiItem? {
            author ?: return null
            quote ?: return null
            return QuoteUiItem(
                quote.id,
                author.id,
                quote.message.quoted(),
                author.displayName()
            )
        }
    }
}

fun Author.displayName(): String {
    return "$firstName $lastName"
}