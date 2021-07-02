package it.willuz.cleandroid.scenes.detail

import android.content.Context
import androidx.lifecycle.*
import it.willuz.cleandroid.entity.db.LocalDatabase
import it.willuz.cleandroid.scenes.displayName
import it.willuz.cleandroid.util.reassign
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class QuoteDetailsViewModel(private val quoteId: Int, private val db: LocalDatabase): ViewModel() {

    private var _details = MutableLiveData(QuoteDetailsUiData("", "", "", ""))
    val details: LiveData<QuoteDetailsUiData> get() = _details

    init {
        viewModelScope.launch {
            val quote = db.quotesDao().getQuote(quoteId) ?: return@launch
            val author = db.authorDao().getAuthor(quote.author) ?: return@launch

            _details.reassign {
                QuoteDetailsUiData(
                    quote.message,
                    author.displayName(),
                    SimpleDateFormat("dd/MM/yyyy",
                        Locale.getDefault()).format(quote.timestamp),
                    author.role
                ) }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class QuoteDetailsViewModelFactory(context: Context, private val quoteId: Int): ViewModelProvider.Factory {
    private val db = LocalDatabase.getInstance(context)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteDetailsViewModel(quoteId, db) as T
    }

}