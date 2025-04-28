package com.clsk.quotemaker.data.repository

import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.network.QuoteApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(private val apiService: QuoteApiService) {

    // In-memory cache of favorites (in a real app, use Room database)
    private val favorites = mutableListOf<Quote>()

    suspend fun getRandomQuote(): Quote {
        return withContext(Dispatchers.IO) {
            val response = apiService.getRandomQuote()
            Quote(
                content = response.content,
                author = response.author
            )
        }
    }
    fun addToFavorites(quote: Quote) {
        favorites.add(quote)
    }

    fun getFavorites(): List<Quote> {
        return favorites
    }

    fun removeFromFavorites(quote: Quote) {
        favorites.remove(quote)
    }
}