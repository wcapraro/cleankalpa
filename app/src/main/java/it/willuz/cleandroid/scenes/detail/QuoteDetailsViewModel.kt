package it.willuz.cleandroid.scenes.detail

import android.content.Context
import androidx.lifecycle.*
import it.willuz.cleandroid.data.repository.QuotesRepository
import it.willuz.cleandroid.data.db.LocalDatabase
import it.willuz.cleandroid.domain.GetAuthorUseCase
import it.willuz.cleandroid.domain.GetQuoteDetailsUseCase
import it.willuz.cleandroid.domain.IGetQuoteDetailsUseCase
import it.willuz.cleandroid.util.reassign
import kotlinx.coroutines.launch

class QuoteDetailsViewModel(
    private val quoteId: Int,
    private val quoteDetails: IGetQuoteDetailsUseCase): ViewModel() {

    private var _details = MutableLiveData(QuoteDetailsUiData("", "", "", ""))
    val details: LiveData<QuoteDetailsUiData> get() = _details

    init {
        viewModelScope.launch {
            getQuoteDetailsSuspending(quoteId)
        }
    }

    private suspend fun getQuoteDetailsSuspending(id: Int) {
        quoteDetails.getQuote(id)?.let { q ->
            _details.reassign { q.asUiData() }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class QuoteDetailsViewModelFactory(context: Context, private val quoteId: Int): ViewModelProvider.Factory {
    private val db = LocalDatabase.getInstance(context)
    private val repo = QuotesRepository(db.quotesDao(), db.authorDao())
    private val getAuthor = GetAuthorUseCase(repo)
    private val quoteDetails = GetQuoteDetailsUseCase(repo, getAuthor)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteDetailsViewModel(quoteId, quoteDetails) as T
    }

}