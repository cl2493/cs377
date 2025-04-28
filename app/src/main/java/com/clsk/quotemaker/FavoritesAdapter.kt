package com.clsk.quotemaker.ui.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clsk.quotemaker.R
import com.clsk.quotemaker.data.model.Quote

class FavoritesAdapter(
    private var quotes: List<Quote>,
    private val onRemoveClicked: (Quote) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentTextView: TextView = view.findViewById(R.id.quoteContent)
        val authorTextView: TextView = view.findViewById(R.id.quoteAuthor)
        val removeButton: Button = view.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        holder.contentTextView.text = "\"${quote.content}\""
        holder.authorTextView.text = "- ${quote.author}"
        holder.removeButton.setOnClickListener {
            onRemoveClicked(quote)
        }
    }

    override fun getItemCount() = quotes.size

    fun updateQuotes(newQuotes: List<Quote>) {
        quotes = newQuotes
        notifyDataSetChanged()
    }
}