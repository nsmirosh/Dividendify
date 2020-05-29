package com.example.dividendify.models

import androidx.lifecycle.MutableLiveData

class Search() {

    var searchString: MutableLiveData<String> = MutableLiveData()


    fun modifyString(stringToModify: String) {
        searchString.value = stringToModify + "123"
    }
}