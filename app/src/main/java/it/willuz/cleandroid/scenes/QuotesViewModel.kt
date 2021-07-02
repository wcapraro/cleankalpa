package it.willuz.cleandroid.scenes

import android.content.Context
import androidx.lifecycle.*
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.entity.db.LocalDatabase
import it.willuz.cleandroid.repository.LocalDataSource
import it.willuz.cleandroid.repository.QuotesRepository
import it.willuz.cleandroid.util.DispatcherManager
import it.willuz.cleandroid.util.IDispatcherManager
import it.willuz.cleandroid.util.reassign
import kotlinx.coroutines.launch

class QuotesViewModel(
    private val dispatcher: IDispatcherManager,
    private val repository: LocalDataSource
): ViewModel() {

    private val maxQuotes = 5

    private var _viewState = MutableLiveData(QuotesViewState())
    val viewState: LiveData<QuotesViewState> get() = _viewState

    private var _quotes = MutableLiveData(listOf<Quote>())
    val quotes: LiveData<List<Quote>> get() = _quotes

    fun requestRefresh() {
        viewModelScope.launch(dispatcher.background) {
            _viewState.reassign { it.loading(true) }
            val items = getQuotesSuspending(maxQuotes)
            _quotes.postValue(items)
            _viewState.reassign { it.loading(false).empty(items.isEmpty()) }
        }
    }

    private suspend fun getQuotesSuspending(count: Int): List<Quote> {
        val quotes = repository.getQuotes()
        return if (quotes.size <= count) quotes else quotes.subList(0, count)
    }
}

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(context: Context): ViewModelProvider.Factory {
    private val db = LocalDatabase.getInstance(context)
    private val repo = QuotesRepository(db.quotesDao(), db.authorDao())

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(DispatcherManager, repo) as T
    }

}