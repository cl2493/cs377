package com.clsk.quotemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.data.repository.QuoteRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getRandomQuote() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val result = repository.getRandomQuote()
                _quote.value = result
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = "Failed to load quote: ${e.message}"
                _isLoading.value = false
            }
        }
    }

    fun addToFavorites(quote: Quote) {
        viewModelScope.launch {
            repository.addToFavorites(quote)
        }
    }
}