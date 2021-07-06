package it.willuz.cleandroid.scenes.quotes

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import it.willuz.cleandroid.databinding.ActivityQuotesBinding
import it.willuz.cleandroid.navigation.Navigation
import it.willuz.cleandroid.navigation.toIntent
import it.willuz.cleandroid.util.BaseActivity
import it.willuz.cleandroid.util.visible

class QuotesActivity : BaseActivity<QuotesViewModel>(), QuotesCallbacks {

    private var adapter = QuotesAdapter()
    private lateinit var binding: ActivityQuotesBinding
    override lateinit var viewModel: QuotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, QuotesViewModelFactory(this))
            .get(QuotesViewModel::class.java)

        viewModel.viewState.observe(this) { onViewState(it) }
        viewModel.quotes.observe(this) { onQuotes(it) }
        viewModel.routing.observe(this) { onRoute(it) }
    }

    override fun onStart() {
        super.onStart()
        adapter.callbacks = this
        binding.recycler.adapter = adapter
        binding.refreshButton.setOnClickListener { viewModel.requestRefresh() }
    }

    override fun onStop() {
        super.onStop()
        binding.refreshButton.setOnClickListener(null)
        adapter.callbacks = null
        binding.recycler.adapter = null
    }

    private fun onViewState(state: QuotesViewState) {
        binding.recycler.visible(!state.emptyUiVisible)
        binding.progress.visible(state.isLoading)
        binding.empty.visible(state.emptyUiVisible)
    }

    private fun onQuotes(quotes: List<QuoteUiItem>) {
        adapter.setItems(quotes)
    }

    private fun onRoute(navi: Navigation) {
        when (navi) {
            is Navigation.ToQuoteDetailsNavigation -> {
                navi.toIntent(this)?.let {
                    startActivity(it)
                }
            }
            else -> { /* no navigation required */ }
        }
    }

    override fun onQuoteSelected(sender: QuotesAdapter, quote: QuoteUiItem) {
        viewModel.onQuoteSelected(quote)
    }
}