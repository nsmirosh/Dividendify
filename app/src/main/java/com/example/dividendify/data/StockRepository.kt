package com.example.dividendify.data

import androidx.lifecycle.MutableLiveData
import com.example.dividendify.BuildConfig
import com.example.dividendify.models.*
import com.example.dividendify.models.enums.ReportFrequency
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class StockRepository : BaseRepository() {

    var stockQuote: MutableLiveData<StockQuote> = MutableLiveData()

    var companyProfile: MutableLiveData<CompanyProfile> = MutableLiveData()

    var financials: MutableLiveData<List<Financials>> = MutableLiveData()

    var symbols: MutableLiveData<List<Symbol>> = MutableLiveData()

    fun getQuoteForStock(symbol: String) {
        val quoteCall =
            buildRetrofit().create(StocksService::class.java).getQuote(symbol, BuildConfig.API_KEY)
        val thread = Thread {
            val response = quoteCall!!.execute()
            stockQuote.postValue(response.body())
        }
        thread.start()
    }

    fun getCompanyProfile(symbol: String) {
        val companyProfileCall = buildRetrofit().create(StocksService::class.java)
            .getCompanyProfile(symbol, BuildConfig.API_KEY)

        val thread = Thread {
            val response = companyProfileCall.execute()
            companyProfile.postValue(response.body())
        }
        thread.start()
    }

    fun getFinancials(symbol: String, frequency: ReportFrequency?) {
        val financialsCall = buildRetrofit().create(StocksService::class.java)
            .getFinancials(symbol, frequency!!.freq, BuildConfig.API_KEY)
        val thread = Thread {
            val response = financialsCall.execute()
            financials.postValue(response.body()!!.data)
        }
        thread.start()
    }


    fun getSymbols(exchange: String) {
        val financialsCall = buildRetrofit().create(StocksService::class.java)
            .getAllSymbols(exchange,  BuildConfig.API_KEY)
        val thread = Thread {
            val response = financialsCall.execute()
            symbols.postValue(response.body())
        }
        thread.start()
    }
}