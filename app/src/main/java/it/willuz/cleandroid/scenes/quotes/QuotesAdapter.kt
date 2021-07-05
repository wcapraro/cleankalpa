package it.willuz.cleandroid.scenes.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.willuz.cleandroid.R

class QuotesAdapter: RecyclerView.Adapter<QuoteViewHolder>() {

    private var items = mutableListOf<QuoteUiItem>()

    var callbacks: QuotesCallbacks? = null

    fun setItems(items: Collection<QuoteUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: QuoteViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun getItemCount(): Int = items.count()
}

class QuoteViewHolder(itemView: View, private val adapter: QuotesAdapter):
    RecyclerView.ViewHolder(itemView),
    View.OnClickListener
{

    private var boundItem: QuoteUiItem? = null

    private val quote: TextView = itemView.findViewById(R.id.quote)
    private val author: TextView = itemView.findViewById(R.id.author)

    fun bind(item: QuoteUiItem) {
        itemView.setOnClickListener(this)
        boundItem = item
        quote.text = item.quote
        author.text = item.author
    }

    fun unbind() {
        boundItem = null
        itemView.setOnClickListener(null)
    }

    override fun onClick(v: View?) {
        adapter.callbacks?.let { cbak ->
            boundItem?.let { item ->
                cbak.onQuoteSelected(adapter, item)
            }
        }
    }
}

interface QuotesCallbacks {
    fun onQuoteSelected(sender: QuotesAdapter, quote: QuoteUiItem)
}