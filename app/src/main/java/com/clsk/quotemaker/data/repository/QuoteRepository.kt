package com.clsk.quotemaker.data.repository

import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.network.QuoteApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(private val apiService: QuoteApiService) {

    // In-memory cache of favorites
    private val favorites = mutableListOf<Quote>()

    suspend fun getRandomQuote(): Quote {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getRandomQuote()
                // ZenQuotes returns a list with one quote
                val zenQuote = response.firstOrNull()

                // Convert ZenQuote to your app's Quote model
                Quote(
                    content = zenQuote?.content ?: "Error loading quote",
                    author = zenQuote?.author ?: "Unknown"
                )
            } catch (e: Exception) {
                // Provide a fallback quote if the API call fails
                Quote(
                    content = "Failed to load quote: ${e.message}",
                    author = "Error"
                )
            }
        }
    }

    fun addToFavorites(quote: Quote) {
        // Check if the quote is already in favorites to avoid duplicates
        if (!favorites.any { it.content == quote.content && it.author == quote.author }) {
            // Create a new Quote object with isFavorite set to true
            val favoriteQuote = Quote(
                content = quote.content,
                author = quote.author,
                isFavorite = true
            )
            favorites.add(favoriteQuote)
        }
    }

    fun getFavorites(): List<Quote> {
        return favorites
    }

    fun removeFromFavorites(quote: Quote) {
        favorites.remove(quote)
    }
}