package com.clsk.quotemaker.data.repository

import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object QuoteRepository {

    private val apiService = RetrofitClient.quoteApiService
    private val favorites = mutableListOf<Quote>()

    suspend fun getRandomQuote(): Quote {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRandomQuote()
                val zenQuote = response.firstOrNull()
                Quote(
                    content = zenQuote?.content ?: "Error loading quote",
                    author = zenQuote?.author ?: "Unknown"
                )
            } catch (e: Exception) {
                Quote(
                    content = "Failed to load quote: ${e.message}",
                    author = "Error"
                )
            }
        }
    }

    fun addToFavorites(quote: Quote) {
        if (!favorites.any { it.content == quote.content && it.author == quote.author }) {
            favorites.add(
                Quote(
                    content = quote.content,
                    author = quote.author,
                    isFavorite = true
                )
            )
        }
    }

    fun getFavorites(): List<Quote> {
        return favorites
    }

    fun removeFromFavorites(quote: Quote) {
        favorites.remove(quote)
    }
}
