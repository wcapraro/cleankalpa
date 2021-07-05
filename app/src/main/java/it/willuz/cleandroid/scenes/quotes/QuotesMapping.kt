package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.domain.QuoteModel
import it.willuz.cleandroid.util.quoted

fun QuoteModel.asUiItem(): QuoteUiItem {
    return QuoteUiItem(this.id,
        this.quote.quoted(),
        "${this.author?.firstName ?: ""} ${this.author?.lastName ?: ""}")
}