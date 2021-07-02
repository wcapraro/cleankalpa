package it.willuz.cleandroid.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.willuz.cleandroid.R
import it.willuz.cleandroid.entity.Quote
import it.willuz.cleandroid.scenes.QuoteUiItem

class QuotesAdapter: RecyclerView.Adapter<QuoteViewHolder>() {

    private var items = mutableListOf<QuoteUiItem>()

    fun setItems(items: Collection<QuoteUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()
}

class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var boundItem: QuoteUiItem? = null

    private val quote: TextView = itemView.findViewById(R.id.quote)
    private val author: TextView = itemView.findViewById(R.id.author)

    fun bind(item: QuoteUiItem) {
        boundItem = item
        quote.text = item.quote
        author.text = item.author
    }
}