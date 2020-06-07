package com.example.dividendify.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dividendify.data.StockRepository
import com.example.dividendify.models.Search
import com.example.dividendify.models.StockQuote

class HomeViewModel : ViewModel() {
    private var search: Search? = null

    val searchRepo = StockRepository()

    val stockQuote = searchRepo.stockQuote

    fun onSearchClicked(searchString: String) {
        searchRepo.getQuoteForStock(searchString)
    }

    fun getStockQuotes(): LiveData<StockQuote> {
        return searchRepo.stockQuote
    }


}