package com.example.dividendify.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dividendify.data.StockRepository
import com.example.dividendify.models.StockQuote

class StockDetailsViewModel : ViewModel() {

    val stockRepo = StockRepository()

    val stockQuote: LiveData<StockQuote> = stockRepo.stockQuote

    fun onCreate(symbol: String) {
        stockRepo.getQuoteForStock(symbol)
//        stockRepo.getDividendHistory(symbol, "2019-02-01", "2020-02-01")
    }
}