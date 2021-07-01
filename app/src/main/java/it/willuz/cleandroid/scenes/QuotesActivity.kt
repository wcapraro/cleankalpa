package it.willuz.cleandroid.scenes

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import it.willuz.cleandroid.databinding.ActivityQuotesBinding
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.util.BaseActivity
import it.willuz.cleandroid.util.QuotesAdapter
import it.willuz.cleandroid.util.visible

class QuotesActivity : BaseActivity<QuotesViewModel>() {

    private var adapter = QuotesAdapter()
    private lateinit var binding: ActivityQuotesBinding
    override lateinit var viewModel: QuotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, QuotesViewModelFactory())
            .get(QuotesViewModel::class.java)

        viewModel.viewState.observe(this) { onViewState(it) }
        viewModel.quotes.observe(this) { onQuotes(it) }
    }

    override fun onStart() {
        super.onStart()
        binding.recycler.adapter = adapter
        binding.refreshButton.setOnClickListener { viewModel.requestRefresh() }
    }

    override fun onStop() {
        super.onStop()
        binding.refreshButton.setOnClickListener(null)
        binding.recycler.adapter = null
    }

    private fun onViewState(state: QuotesViewState) {
        binding.recycler.visible(!state.emptyUiVisible)
        binding.progress.visible(state.isLoading)
        binding.empty.visible(state.emptyUiVisible)
    }

    private fun onQuotes(quotes: List<Quote>) {
        adapter.setItems(quotes)
    }
}