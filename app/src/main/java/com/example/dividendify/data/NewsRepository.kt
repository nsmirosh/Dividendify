package com.example.dividendify.data

import androidx.lifecycle.MutableLiveData
import com.example.dividendify.models.News
import com.example.dividendify.models.enums.NewsCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository : BaseRepository() {

    var news: MutableLiveData<List<News>> = MutableLiveData()
    var companyNews: MutableLiveData<List<News>> = MutableLiveData()

    val newsService = buildRetrofit().create(NewsService::class.java)

    fun getNews(category: NewsCategory, minId: String?) {
        newsService.getGeneralNews(category.value, minId)
            ?.enqueue(object : Callback<List<News>> {
                override fun onResponse(
                    call: Call<List<News>?>?,
                    response: Response<List<News>?>?
                ) {
                    news.value = response!!.body()
                }

                override fun onFailure(call: Call<List<News>?>?, t: Throwable?) {

                }
            })
    }


    fun getCompanyNews(symbol: String, from: String, to: String) {
        newsService.getCompanyNews(symbol, from, to)
            ?.enqueue(object : Callback<List<News>> {
                override fun onResponse(
                    call: Call<List<News>?>?,
                    response: Response<List<News>?>?
                ) {
                    companyNews.value = response!!.body()
                }

                override fun onFailure(call: Call<List<News>?>?, t: Throwable?) {

                }
            })
    }
}