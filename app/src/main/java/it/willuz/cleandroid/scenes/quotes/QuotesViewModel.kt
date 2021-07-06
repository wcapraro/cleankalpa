package it.willuz.cleandroid.scenes.quotes

import android.content.Context
import androidx.lifecycle.*
import it.willuz.cleandroid.data.db.LocalDatabase
import it.willuz.cleandroid.domain.GetAuthorUseCase
import it.willuz.cleandroid.domain.GetRandomQuotesUseCase
import it.willuz.cleandroid.domain.IGetRandomQuotesUseCase
import it.willuz.cleandroid.data.repository.QuotesRepository
import it.willuz.cleandroid.navigation.Navigation
import it.willuz.cleandroid.util.DispatcherManager
import it.willuz.cleandroid.util.IDispatcherManager
import it.willuz.cleandroid.util.reassign
import kotlinx.coroutines.launch

class QuotesViewModel(
    private val dispatcher: IDispatcherManager,
    private val getQuotes: IGetRandomQuotesUseCase
): ViewModel() {

    private val maxQuotes = 5

    private var _viewState = MutableLiveData(QuotesViewState())
    val viewState: LiveData<QuotesViewState> get() = _viewState

    private var _quotes = MutableLiveData(listOf<QuoteUiItem>())
    val quotes: LiveData<List<QuoteUiItem>> get() = _quotes

    private var _routing: MutableLiveData<Navigation> = MutableLiveData(Navigation.None)
    val routing: LiveData<Navigation> get() = _routing

    fun requestRefresh() {
        viewModelScope.launch(dispatcher.background) {
            _viewState.reassign { it.loading(true) }
            val items = getQuotesSuspending(maxQuotes)
            _quotes.postValue(items)
            _viewState.reassign { it.loading(false).empty(items.isEmpty()) }
        }
    }

    fun onQuoteSelected(quote: QuoteUiItem) {
        _routing.postValue(Navigation.ToQuoteDetailsNavigation(quote.id))
    }

    private suspend fun getQuotesSuspending(count: Int): List<QuoteUiItem> {
        return getQuotes.getRandomQuotes(count).map { it.asUiItem() }
    }
}

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(context: Context): ViewModelProvider.Factory {
    private val db = LocalDatabase.getInstance(context)
    private val repo = QuotesRepository(db.quotesDao(), db.authorDao())
    private val getQuotes = GetRandomQuotesUseCase(repo, GetAuthorUseCase(repo))

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(DispatcherManager, getQuotes) as T
    }

}