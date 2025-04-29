package com.clsk.quotemaker.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.data.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

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
                val fetchedQuote = QuoteRepository.getRandomQuote()
                _quote.value = fetchedQuote
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addToFavorites(quote: Quote) {
        QuoteRepository.addToFavorites(quote)
    }

    fun getFavorites(): List<Quote> {
        return QuoteRepository.getFavorites()
    }

    fun removeFromFavorites(quote: Quote) {
        QuoteRepository.removeFromFavorites(quote)
    }
}
