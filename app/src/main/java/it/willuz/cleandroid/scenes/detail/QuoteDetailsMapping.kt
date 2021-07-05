package it.willuz.cleandroid.scenes.detail

import it.willuz.cleandroid.domain.QuoteModel
import it.willuz.cleandroid.domain.displayName
import it.willuz.cleandroid.util.quoted
import java.text.SimpleDateFormat
import java.util.*

fun QuoteModel.asUiData(locale: Locale = Locale.getDefault()): QuoteDetailsUiData {
    return QuoteDetailsUiData(this.quote.quoted(),
        this.author?.displayName ?: "Unknown",
        SimpleDateFormat("dd/MM/yyyy", locale).format(this.date),
        this.author?.role?.name ?: ""
    )
}