package com.clsk.quotemaker.network

// import necessary packages
import com.clsk.quotemaker.data.model.QuoteResponse
import retrofit2.http.GET

interface QuoteApiService {
    @GET("random")
    suspend fun getRandomQuote(): QuoteResponse

}
