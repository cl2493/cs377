// Create this file at:
// app/src/main/java/com/clsk/quotemaker/network/RetrofitClient.kt

package com.clsk.quotemaker.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.quotable.io/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quoteApiService: QuoteApiService by lazy {
        instance.create(QuoteApiService::class.java)
    }
}