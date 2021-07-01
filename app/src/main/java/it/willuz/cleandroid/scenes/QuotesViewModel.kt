package it.willuz.cleandroid.scenes

import androidx.lifecycle.*
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.util.reassign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuotesViewModel: ViewModel() {

    private var _viewState = MutableLiveData(QuotesViewState())
    val viewState: LiveData<QuotesViewState> get() = _viewState

    private var _quotes = MutableLiveData(listOf<Quote>())
    val quotes: LiveData<List<Quote>> get() = _quotes

    fun requestRefresh() {
        viewModelScope.launch {
            _viewState.reassign { it.loading(true) }
            val items = getQuotesSuspending(10)
            _quotes.postValue(items)
            _viewState.reassign { it.loading(false).empty(items.isEmpty()) }
        }
    }

    private suspend fun getQuotesSuspending(count: Int): List<Quote> {
        // Simulate work
        delay(3000L)
        return emptyList()
    }
}

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel() as T
    }

}