package com.example.dividendify.data

import androidx.lifecycle.MutableLiveData
import com.example.dividendify.BuildConfig
import com.example.dividendify.models.CompanyProfile
import com.example.dividendify.models.Dividend
import com.example.dividendify.models.StockQuote
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class StockRepository {

    var stockQuote: MutableLiveData<StockQuote> = MutableLiveData()

    var companyProfile: MutableLiveData<CompanyProfile> = MutableLiveData()

    var dividendHistory: MutableLiveData<List<Dividend?>> = MutableLiveData()

    fun getQuoteForStock(symbol: String) {
        val stuff = buildStockService().getQuote(symbol, BuildConfig.API_KEY)

        val thread = Thread {
            val response = stuff?.execute()
            stockQuote.postValue(response?.body())
        }
        thread.start()
    }

    fun getCompanyProfile(symbol: String) {
        val stuff = buildStockService().getCompanyProfile(symbol, BuildConfig.API_KEY)

        val thread = Thread {
            val response = stuff?.execute()
            companyProfile.postValue(response?.body())
        }
        thread.start()
    }

    fun getDividendHistory(symbol: String, from: String, to: String) {
        val stuff = buildStockService().getDividendHistory(symbol, from, to, BuildConfig.API_KEY)
        val thread = Thread {
            val response = stuff?.execute()
            dividendHistory.postValue(response?.body())
        }
        thread.start()
    }


    fun buildStockService(): StocksService {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        return retrofit.create(StocksService::class.java)
    }


}