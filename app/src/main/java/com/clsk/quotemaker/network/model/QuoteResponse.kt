package com.clsk.quotemaker.data.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("length")
    val length: Int
) {
    val quote: Quote
        get() = Quote(content, author)
}