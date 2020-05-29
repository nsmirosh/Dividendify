package com.example.dividendify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dividendify.models.Search

class SearchViewModel : ViewModel() {

    private var search: Search? = null

    fun init(searchString: String) {
        search = Search()
        search!!.modifyString(searchString)
    }

    fun getModifiedSearchString(): LiveData<String?>? {
        return search!!.searchString
    }
}