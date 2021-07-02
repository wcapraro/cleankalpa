package it.willuz.cleandroid.scenes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import it.willuz.cleandroid.databinding.ActivityQuotesBinding
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.scenes.detail.QuoteDetailsActivity
import it.willuz.cleandroid.util.BaseActivity
import it.willuz.cleandroid.util.QuotesAdapter
import it.willuz.cleandroid.util.QuotesCallbacks
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

    override fun onQuoteSelected(sender: QuotesAdapter, quote: QuoteUiItem) {
        Intent(this, QuoteDetailsActivity::class.java).apply {
            putExtra("extra_quote_id", quote.id)
        }.also { startActivity(it) }
    }
}