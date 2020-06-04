package com.example.dividendify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dividendify.BuildConfig
import com.example.dividendify.models.StockQuote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StockRepository {

    var stockQuote: MutableLiveData<StockQuote> = MutableLiveData()

    fun getQuoteForStock(symbol: String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        val service: StocksService = retrofit.create(StocksService::class.java)
        val stuff = service.getQuote(symbol, BuildConfig.API_KEY)

        val thread = Thread {
            val response = stuff?.execute()
            stockQuote.postValue(response?.body())
        }
        thread.start()
    }


}