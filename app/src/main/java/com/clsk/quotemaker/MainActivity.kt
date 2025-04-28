package com.clsk.quotemaker

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.clsk.quotemaker.data.repository.QuoteRepository
import com.clsk.quotemaker.databinding.ActivityMainBinding
import com.clsk.quotemaker.network.RetrofitClient
import com.clsk.quotemaker.ui.viewmodel.QuoteViewModel
import com.clsk.quotemaker.ui.viewmodel.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        val repository = QuoteRepository(RetrofitClient.quoteApiService)
        val viewModelFactory = QuoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuoteViewModel::class.java]
        // Set up observers
        setupObservers()

        // Set up click listeners
        setupClickListeners()

        // Get initial quote
        viewModel.getRandomQuote()
    }

    private fun setupObservers() {
        viewModel.quote.observe(this) { quote ->
            binding.quote.text = quote.content
            binding.author.text = "- ${quote.author}"
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupClickListeners() {
        binding.btnGenerate.setOnClickListener {
            viewModel.getRandomQuote()
        }

        binding.btnFavorite.setOnClickListener {
            viewModel.quote.value?.let { quote ->
                viewModel.addToFavorites(quote)
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }
}