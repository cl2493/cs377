package com.clsk.quotemaker.data.model

data class Quote(
    val content: String,
    val author: String,
    val isFavorite: Boolean = false
)