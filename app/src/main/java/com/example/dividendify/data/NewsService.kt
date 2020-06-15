package com.example.dividendify.data

import com.example.dividendify.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("news")
    fun getGeneralNews(
        @Query("category") category: String,
        @Query("minId") minId: String?
    ): Call<List<News>>?


    @GET("company-news")
    fun getCompanyNews(
        @Query("symbol") symbol: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): Call<List<News>>?
}