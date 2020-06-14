package com.example.dividendify.data

import androidx.lifecycle.MutableLiveData
import com.example.dividendify.BuildConfig
import com.example.dividendify.models.News
import com.example.dividendify.models.enums.NewsCategory

class NewsRepository : BaseRepository() {

    var news: MutableLiveData<List<News>> = MutableLiveData()

    val newsService = buildRetrofit().create(NewsService::class.java)

    fun getNews(category: NewsCategory, minId: String?) {
        val stuff = newsService.getGeneralNews(category.value, minId, BuildConfig.API_KEY)
        val thread = Thread {
            val response = stuff!!.execute()
            news.postValue(response.body())
        }
        thread.start()
    }
}