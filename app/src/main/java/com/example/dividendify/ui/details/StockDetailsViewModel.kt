package com.example.dividendify.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dividendify.data.StockRepository
import com.example.dividendify.models.Financials
import com.example.dividendify.models.FinancialsResponse
import com.example.dividendify.models.ReportFrequency
import com.example.dividendify.models.StockQuote

class StockDetailsViewModel : ViewModel() {

    val stockRepo = StockRepository()

    val stockQuote: LiveData<StockQuote> = stockRepo.stockQuote
    val financials: LiveData<List<Financials>> = stockRepo.financials

    fun onCreate(symbol: String) {
        stockRepo.getQuoteForStock(symbol)
        stockRepo.getFinancials(symbol, /*ReportFrequency.ANNUAL*/null)
    }
}