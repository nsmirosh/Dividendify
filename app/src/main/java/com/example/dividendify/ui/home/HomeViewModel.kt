package com.example.dividendify.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dividendify.data.NewsRepository
import com.example.dividendify.data.StockRepository
import com.example.dividendify.models.CompanyProfile
import com.example.dividendify.models.Financials
import com.example.dividendify.models.News
import com.example.dividendify.models.Search
import com.example.dividendify.models.enums.NewsCategory

class HomeViewModel : ViewModel() {

    val searchRepo = StockRepository()

    val newsRepo = NewsRepository()

    val companyProfile: LiveData<CompanyProfile> = searchRepo.companyProfile

    val news: LiveData<List<News>> = newsRepo.news

    fun onSearchClicked(symbol: String) {
        searchRepo.getCompanyProfile(symbol)
    }

    fun onCreate() {
        newsRepo.getNews(NewsCategory.GENERAL, null)
    }
}