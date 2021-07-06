package it.willuz.cleandroid.scenes.quotes

import android.app.Activity
import android.content.Context
import it.willuz.cleandroid.data.db.LocalDatabase
import it.willuz.cleandroid.data.repository.QuotesRepository
import it.willuz.cleandroid.domain.GetAuthorUseCase
import it.willuz.cleandroid.domain.GetRandomQuotesUseCase
import it.willuz.cleandroid.util.BaseViperPresenter
import it.willuz.cleandroid.util.DispatcherManager
import it.willuz.cleandroid.util.IDispatcherManager
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class QuotesPresenter(view: QuotesView?,
    private val dispatcher: IDispatcherManager = DispatcherManager)
    : BaseViperPresenter<QuotesView, QuotesBusinessLogic, QuotesRoutingLogic>(view),
    QuotesPresentationLogic,
        QuotesInteractorOutput
{
    override fun createInteractor(): QuotesBusinessLogic {
        val ctx = (view as? Context) ?: error("Shall never happen")
        val db = LocalDatabase.getInstance(ctx)
        val repo = QuotesRepository(db.quotesDao(), db.authorDao())
        val getQuotes = GetRandomQuotesUseCase(repo, GetAuthorUseCase(repo))
        return QuotesInteractor(this, getQuotes)
    }

    override fun createRouter(): QuotesRoutingLogic {
        (view as? Activity)?.let {
            return QuotesRouter(WeakReference(it))
        } ?: throw error("Shall never happen")
    }

    override fun lifecycleOnCreate() {
        super.lifecycleOnCreate()
        view?.apply {
            onLoadingUiVisible(false)
            onRecyclerVisible(false)
            onEmptyUiVisible(true)
        }
    }

    override fun refreshClicked() {
        view?.onLoadingUiVisible(true)
        presenterScope?.launch(dispatcher.background) {
            interactor?.requestLoadNRandomQuotes(5, presenterScope!!)
        }
    }

    override fun quoteSelected(item: QuoteUiItem) {
        router?.showQuoteDetails(item)
    }

    override fun responseLoadNRandomQuotes(items: List<QuoteUiItem>) {
        presenterScope?.launch(dispatcher.ui) {
            view?.apply {
                onLoadingUiVisible(false)
                onRecyclerVisible(items.isNotEmpty())
                onEmptyUiVisible(items.isEmpty())
                onQuotes(items)
            }
        }
    }

    override fun onError(message: String) {
        presenterScope?.launch(dispatcher.ui) {
            view?.apply {
                onLoadingUiVisible(false)
                onEmptyUiVisible(true)
                onRecyclerVisible(false)
            }
        }
    }

}