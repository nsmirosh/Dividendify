package com.example.dividendify.data

import com.example.dividendify.models.Dividend
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
}