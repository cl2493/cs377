package com.clsk.quotemaker.data.model.Quote

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("content")
    val content: String,
    @SerializedName("author")
    val author: String
)
