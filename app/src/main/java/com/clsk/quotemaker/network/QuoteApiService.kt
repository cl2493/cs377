package com.clsk.quotemaker.network

import com.clsk.quotemaker.data.model.ZenQuote
import retrofit2.http.GET

interface QuoteApiService {
    @GET("random")
    suspend fun getRandomQuote(): List<ZenQuote>
}