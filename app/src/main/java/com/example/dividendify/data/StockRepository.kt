package com.example.dividendify.data

import androidx.lifecycle.MutableLiveData
import com.example.dividendify.BuildConfig
import com.example.dividendify.models.*
import com.example.dividendify.models.enums.MetricType
import com.example.dividendify.models.enums.ReportFrequency
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class StockRepository : BaseRepository() {

    var stockQuote: MutableLiveData<StockQuote> = MutableLiveData()

    var companyProfile: MutableLiveData<CompanyProfile> = MutableLiveData()

    var financials: MutableLiveData<List<Financials>> = MutableLiveData()

    var basicFinancials: MutableLiveData<BasicFinancials> = MutableLiveData()

    var symbols: MutableLiveData<List<Symbol>> = MutableLiveData()

    fun getQuoteForStock(symbol: String) {
        buildRetrofit().create(StocksService::class.java).getQuote(symbol)
            ?.enqueue(object : Callback<StockQuote> {
                override fun onResponse(
                    call: Call<StockQuote>?,
                    response: Response<StockQuote>?
                ) {
                    stockQuote.value = response!!.body()
                }

                override fun onFailure(call: Call<StockQuote>, t: Throwable) {
                }
            })
    }

    fun getCompanyProfile(symbol: String) {
        buildRetrofit().create(StocksService::class.java)
            .getCompanyProfile(symbol)
            .enqueue(object : Callback<CompanyProfile> {
                override fun onResponse(
                    call: Call<CompanyProfile>?,
                    response: Response<CompanyProfile>?
                ) {
                    companyProfile.value = response!!.body()
                }

                override fun onFailure(call: Call<CompanyProfile>, t: Throwable) {
                }
            })
    }

    fun getFinancials(symbol: String, frequency: ReportFrequency?) {
        buildRetrofit().create(StocksService::class.java)
            .getFinancials(symbol, frequency!!.freq)
            .enqueue(object : Callback<FinancialsResponse> {
                override fun onResponse(
                    call: Call<FinancialsResponse>?,
                    response: Response<FinancialsResponse>?
                ) {
                    financials.value = response!!.body()!!.data
                }

                override fun onFailure(call: Call<FinancialsResponse>, t: Throwable) {
                }
            })
    }

    fun getBasicFinancials(symbol: String, metricType: MetricType) {
        buildRetrofit().create(StocksService::class.java)
            .getBasicFinancials(symbol, metricType.value)
            .enqueue(object : Callback<BasicFinancialsResponse> {
                override fun onResponse(
                    call: Call<BasicFinancialsResponse>?,
                    response: Response<BasicFinancialsResponse>?
                ) {
                    basicFinancials.value = response!!.body()!!.metric
                }

                override fun onFailure(call: Call<BasicFinancialsResponse>, t: Throwable) {
                }
            })
    }

    fun getSymbols(exchange: String) {
        buildRetrofit().create(StocksService::class.java)
            .getAllSymbols(exchange)
            .enqueue(object : Callback<List<Symbol>> {
                override fun onResponse(
                    call: Call<List<Symbol>>?,
                    response: Response<List<Symbol>>?
                ) {
                    symbols.value = response!!.body()
                }

                override fun onFailure(call: Call<List<Symbol>>, t: Throwable) {
                }
            })
    }
}