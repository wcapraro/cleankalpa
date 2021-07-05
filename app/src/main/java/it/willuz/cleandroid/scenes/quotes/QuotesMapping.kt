package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.domain.QuoteModel
import it.willuz.cleandroid.domain.displayName
import it.willuz.cleandroid.util.quoted

fun QuoteModel.asUiItem(): QuoteUiItem {
    return QuoteUiItem(this.id,
        this.quote.quoted(),
        author?.displayName ?: "")
}