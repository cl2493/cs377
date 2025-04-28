package com.clsk.quotemaker.data.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("content")
    val content: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("tags")
    val tags: List<String> = emptyList(),
    @SerializedName("_id")
    val id: String = "",
    var isFavorite: Boolean = false
)