package com.clsk.quotemaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.clsk.quotemaker.data.model.Quote
import com.clsk.quotemaker.databinding.ActivityMainBinding
import com.clsk.quotemaker.ui.viewmodel.QuoteViewModel
import com.clsk.quotemaker.ui.viewmodel.QuoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: QuoteViewModel
    private var currentQuote: Quote? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, QuoteViewModelFactory())[QuoteViewModel::class.java]

        setupObservers()
        setupClickListeners()
        setupShareButton()

        viewModel.getRandomQuote()
    }

    private fun setupObservers() {
        viewModel.quote.observe(this) { quote ->
            binding.quote.text = quote.content
            binding.author.text = "- ${quote.author}"
            currentQuote = quote
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

        binding.viewFavoritesButton.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupShareButton() {
        binding.shareButton.setOnClickListener {
            if (currentQuote != null) {
                val quote = currentQuote!!
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "\"${quote.content}\" - ${quote.author}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share quote via"))
            } else {
                Toast.makeText(this, "No quote to share yet!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
