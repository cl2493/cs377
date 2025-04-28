package com.clsk.quotemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.data.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _favorites = MutableLiveData<List<Quote>>(emptyList())
    val favorites: LiveData<List<Quote>> = _favorites

    fun getRandomQuote() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val randomQuote = repository.getRandomQuote()
                _quote.value = randomQuote
            } catch (e: Exception) {
                _error.value = "Failed to load quote: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToFavorites(quote: Quote) {
        val updatedFavorites = _favorites.value?.toMutableList() ?: mutableListOf()
        updatedFavorites.add(quote)
        _favorites.value = updatedFavorites
    }

    fun removeFromFavorites(quote: Quote) {
        val updatedFavorites = _favorites.value?.toMutableList() ?: mutableListOf()
        updatedFavorites.remove(quote)
        _favorites.value = updatedFavorites
    }


    fun getFavorites(): List<Quote> {
        return _favorites.value ?: emptyList()
    }
}
