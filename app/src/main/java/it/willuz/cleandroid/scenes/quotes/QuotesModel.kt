package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.data.entity.Author

data class QuotesViewState(val isLoading: Boolean = false, val emptyUiVisible: Boolean = true) {
    fun loading(l: Boolean) = copy(isLoading = l)
    fun empty(e: Boolean) = copy(emptyUiVisible = e)
}

data class QuoteUiItem(val id: Int, val quote: String, val author: String)

fun Author.displayName(): String {
    return "$firstName $lastName"
}