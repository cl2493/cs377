package com.clsk.quotemaker.data.model

import com.google.gson.annotations.SerializedName

data class ZenQuote(
    @SerializedName("q")
    val content: String,

    @SerializedName("a")
    val author: String
)