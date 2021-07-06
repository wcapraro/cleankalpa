package it.willuz.cleandroid.scenes.detail

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import it.willuz.cleandroid.databinding.ActivityQuoteDetailsBinding
import it.willuz.cleandroid.navigation.NavigationKeys
import it.willuz.cleandroid.util.BaseActivity

class QuoteDetailsActivity : BaseActivity<QuoteDetailsViewModel>() {

    override lateinit var viewModel: QuoteDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityQuoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.getString(NavigationKeys.toQuoteDetails_KeyQuoteId)?.let { quoteId ->
            viewModel = ViewModelProvider(this,
                QuoteDetailsViewModelFactory(baseContext, quoteId.toInt()))
                    .get(QuoteDetailsViewModel::class.java)

            viewModel.details.observe(this) {
                binding.date.text = it.dateSaid
                binding.author.text = it.author
                binding.role.text = it.role
                binding.quote.text = it.quote
            }
        } ?: finish()
    }
}