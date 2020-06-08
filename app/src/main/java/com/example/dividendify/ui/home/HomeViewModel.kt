package com.example.dividendify.ui.home

import androidx.lifecycle.ViewModel
import com.example.dividendify.data.StockRepository
import com.example.dividendify.models.Search

class HomeViewModel : ViewModel() {

    val searchRepo = StockRepository()

    val companyProfile = searchRepo.companyProfile

    fun onSearchClicked(symbol: String) {
        searchRepo.getCompanyProfile(symbol)
    }
}