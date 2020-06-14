package com.example.dividendify.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dividendify.data.NewsRepository
import com.example.dividendify.data.StockRepository
import com.example.dividendify.models.*
import com.example.dividendify.models.enums.NewsCategory

class HomeViewModel : ViewModel() {

    val stockRepo = StockRepository()
    val newsRepo = NewsRepository()

    val companyProfile: LiveData<CompanyProfile> = stockRepo.companyProfile
    val symbols: LiveData<List<Symbol>> = stockRepo.symbols
    val news: LiveData<List<News>> = newsRepo.news

    fun onSearchClicked(symbol: String) {
        stockRepo.getCompanyProfile(symbol)
    }

    fun onCreate() {
        newsRepo.getNews(NewsCategory.GENERAL, null)
//        stockRepo.getSymbols("US")
    }
}