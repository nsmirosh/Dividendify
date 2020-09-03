package com.example.dividendify.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dividendify.R
import com.example.dividendify.models.CompanyProfile
import com.example.dividendify.models.News
import com.orhanobut.logger.Logger
import io.reactivex.rxjava3.core.Observable
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    lateinit var viewModel: HomeViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: NewsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onCreate()
        launch_search_button.setOnClickListener {
            viewModel.onSearchClicked(search.text.toString())
        }

        val nameObserver = Observer<CompanyProfile> { companyProfile ->
            view.findNavController()
                .navigate(HomeFragmentDirections.toStockDetailsFragment(companyProfile))
        }

        val newsObserver = Observer<List<News>> { news ->
            viewAdapter.setItems(news)
        }

        viewManager = LinearLayoutManager(this.requireContext())

        recyclerView = my_recycler_view.apply {
            viewAdapter = NewsAdapter(ArrayList())

            layoutManager = viewManager
            adapter = viewAdapter
        }
        
        var result = ""
        val observable: Observable<String> = Observable.just("Hello")
        observable.subscribe { s -> result = s }

        Logger.d("result = $result");

        viewModel.companyProfile.observe(viewLifecycleOwner, nameObserver)
        viewModel.news.observe(viewLifecycleOwner, newsObserver)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}