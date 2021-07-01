package it.willuz.cleandroid.scenes

data class QuotesViewState(val isLoading: Boolean = false, val emptyUiVisible: Boolean = true) {
    fun loading(l: Boolean) = copy(isLoading = l)
    fun empty(e: Boolean) = copy(emptyUiVisible = e)
}

sealed class QuotesEvents {
    object Noop: QuotesEvents()
}