package it.willuz.cleandroid.scenes.quotes

import android.os.Bundle
import it.willuz.cleandroid.databinding.ActivityQuotesBinding
import it.willuz.cleandroid.util.BaseViperActivity
import it.willuz.cleandroid.util.visible

class QuotesActivity : BaseViperActivity<QuotesPresentationLogic>(), QuotesView, QuotesCallbacks {

    private var adapter = QuotesAdapter()
    private lateinit var binding: ActivityQuotesBinding

    override fun instantiatePresenter(): QuotesPresentationLogic? {
        return QuotesPresenter(this).also { it.initLifecycle() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        adapter.callbacks = this
        binding.recycler.adapter = adapter
        binding.refreshButton.setOnClickListener { presenter?.refreshClicked() }
    }

    override fun onStop() {
        super.onStop()
        binding.refreshButton.setOnClickListener(null)
        adapter.callbacks = null
        binding.recycler.adapter = null
    }

    override fun onQuotes(items: List<QuoteUiItem>) {
        adapter.setItems(items)
    }

    override fun onLoadingUiVisible(visible: Boolean) {
        binding.progress.visible(visible)
    }

    override fun onEmptyUiVisible(visible: Boolean) {
        binding.empty.visible(visible)
    }

    override fun onRecyclerVisible(visible: Boolean) {
        binding.recycler.visible(visible)
    }

    override fun onQuoteSelected(sender: QuotesAdapter, quote: QuoteUiItem) {
        presenter?.quoteSelected(quote)
    }
}