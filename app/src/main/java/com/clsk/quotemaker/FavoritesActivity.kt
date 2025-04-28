package com.clsk.quotemaker

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clsk.quotemaker.data.repository.QuoteRepository
import com.clsk.quotemaker.network.RetrofitClient
import com.clsk.quotemaker.ui.viewmodel.FavoritesAdapter
import com.clsk.quotemaker.ui.viewmodel.QuoteViewModel
import com.clsk.quotemaker.ui.viewmodel.QuoteViewModelFactory

class FavoritesActivity : AppCompatActivity() {

    private lateinit var viewModel: QuoteViewModel
    private lateinit var favoritesAdapter: FavoritesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val repository = QuoteRepository(RetrofitClient.quoteApiService)
        val viewModelFactory = QuoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuoteViewModel::class.java]

        recyclerView = findViewById(R.id.favoritesRecyclerView)
        emptyText = findViewById(R.id.emptyFavoritesText)

        favoritesAdapter = FavoritesAdapter(emptyList()) { quote ->
            viewModel.removeFromFavorites(quote)
            loadFavorites()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesActivity)
            adapter = favoritesAdapter
        }

        loadFavorites()
    }

    private fun loadFavorites() {
        val favorites = viewModel.getFavorites()
        if (favorites.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyText.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyText.visibility = View.GONE
            favoritesAdapter.updateQuotes(favorites)
        }
    }
}
