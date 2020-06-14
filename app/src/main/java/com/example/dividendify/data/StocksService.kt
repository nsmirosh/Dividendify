package com.example.dividendify.data

import com.example.dividendify.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksService {

    @GET("quote")
    fun getQuote(
        @Query("symbol") symbol: String?,
        @Query("token") token: String
    ): Call<StockQuote>?

    @GET("stock/profile2")
    fun getCompanyProfile(
        @Query("symbol") symbol: String?,
        @Query("token") token: String
    ): Call<CompanyProfile>

    @GET("stock/financials-reported")
    fun getFinancials(
        @Query("symbol") symbol: String?,
        @Query("freq") frequency: String?,
        @Query("token") token: String
    ): Call<FinancialsResponse>

    @GET("stock/symbol")
    fun getAllSymbols(
        @Query("exchange") exchange: String,
        @Query("token") token: String
    ): Call<List<Symbol>>

}