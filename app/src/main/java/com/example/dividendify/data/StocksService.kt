package com.example.dividendify.data

import com.example.dividendify.models.Dividend
import com.example.dividendify.models.StockQuote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksService {
    @GET("stock/dividend")
    fun getDividends(
        @Query("symbol") symbol: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
        @Query("token") token: String?
    ): Call<List<Dividend?>?>?

    @GET("quote")
    fun getQuote(
        @Query("symbol") symbol: String?,
        @Query("token") token: String?
    ): Call<StockQuote>?
}