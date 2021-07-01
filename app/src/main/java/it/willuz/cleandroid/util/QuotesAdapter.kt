package it.willuz.cleandroid.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import it.willuz.cleandroid.entity.Quote

class QuotesAdapter: RecyclerView.Adapter<QuoteViewHolder>() {

    private var items = mutableListOf<Quote>()

    fun setItems(items: Collection<Quote>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.count()
}

class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var boundItem: Quote? = null

    private val text: TextView = itemView.findViewById(android.R.id.text1)

    fun bind(item: Quote) {
        boundItem = item
        text.text = item.toString()
    }
}