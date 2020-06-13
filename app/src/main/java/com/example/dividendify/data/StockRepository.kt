package com.example.dividendify.data

import androidx.lifecycle.MutableLiveData
import com.example.dividendify.BuildConfig
import com.example.dividendify.models.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class StockRepository {

    var stockQuote: MutableLiveData<StockQuote> = MutableLiveData()

    var companyProfile: MutableLiveData<CompanyProfile> = MutableLiveData()

    var financials: MutableLiveData<List<Financials>> = MutableLiveData()

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


    fun getFinancials(symbol: String, frequency: ReportFrequency?) {
        val stuff = buildStockService().getFinancials(symbol, frequency!!.freq, BuildConfig.API_KEY)
        val thread = Thread {
            val response = stuff.execute()
            financials.postValue(response.body()!!.data)
        }
        thread.start()
    }


    fun buildStockService(): StocksService {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // connect timeout
            .readTimeout(30, TimeUnit.SECONDS)   // socket timeout
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