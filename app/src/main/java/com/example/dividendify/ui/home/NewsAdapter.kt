package com.example.dividendify.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.dividendify.R
import com.example.dividendify.models.News

class NewsAdapter
    (private var news: List<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun setItems(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var timePosted: TextView
        private var title: TextView
        private var summary: TextView

        init {
            timePosted = view.findViewById(R.id.timePosted)
            title = view.findViewById(R.id.title)
            summary = view.findViewById(R.id.summary)
        }

        fun bind(news: News) {
            timePosted.text = news.datetime.toString()
            title.text = news.headline
            summary.text = news.summary

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_layout, parent, false) as ConstraintLayout
        return NewsViewHolder(textView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size
}
