package it.willuz.cleandroid.scenes

import android.content.Context
import androidx.lifecycle.*
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

    private var _quotes = MutableLiveData(listOf<QuoteUiItem>())
    val quotes: LiveData<List<QuoteUiItem>> get() = _quotes

//    private var _events: MutableLiveData<QuotesEvents> = MutableLiveData(QuotesEvents.Noop)
//    val events: LiveData<QuotesEvents> get() = _events

    fun requestRefresh() {
        viewModelScope.launch(dispatcher.background) {
            _viewState.reassign { it.loading(true) }
            val items = getQuotesSuspending(maxQuotes)
            _quotes.postValue(items)
            _viewState.reassign { it.loading(false).empty(items.isEmpty()) }
        }
    }

    private suspend fun getQuotesSuspending(count: Int): List<QuoteUiItem> {
        val quotes = repository.getQuotes().shuffled()
        val items = quotes.mapNotNull {
            QuoteUiItem.parse(repository.getAuthor(it.author), it)
        }
        return if (count < items.size) items.subList(0, count) else items
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