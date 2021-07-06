package it.willuz.cleandroid.scenes.quotes

import it.willuz.cleandroid.domain.IGetRandomQuotesUseCase
import it.willuz.cleandroid.util.BaseViperInteractor
import it.willuz.cleandroid.util.DispatcherManager
import it.willuz.cleandroid.util.IDispatcherManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QuotesInteractor(presenter: QuotesInteractorOutput,
                       private val useCase: IGetRandomQuotesUseCase,
                       private val dispatcher: IDispatcherManager = DispatcherManager):
    BaseViperInteractor<QuotesInteractorOutput>(presenter),
    QuotesBusinessLogic
{
    override fun requestLoadNRandomQuotes(n: Int, scope: CoroutineScope) {
        scope.launch {
            val items = useCase.getRandomQuotes(n).map { it.asUiItem() }
            output.responseLoadNRandomQuotes(items)
        }
    }
}