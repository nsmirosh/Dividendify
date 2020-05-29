package com.example.dividendify

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dividendify.data.StocksService
import com.example.dividendify.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        launch_search_button.setOnClickListener {
            initDataBinding(search.text.toString())

            callDividendApi()
        }
    }

    private fun initDataBinding(searchString: String) {
        searchViewModel.init(searchString)
        setUpOnSearchConfirmed()
    }

    private fun setUpOnSearchConfirmed() {
        searchViewModel.getModifiedSearchString()!!
            .observe(this, Observer { modifiedSearch -> onGameWinnerChanged(modifiedSearch) })
    }

    fun onGameWinnerChanged(modifiedSearch: String?) {
        modifiedStringToDisplay.text = modifiedSearch
    }

    fun callDividendApi() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()



        val service: StocksService = retrofit.create(StocksService::class.java)
        val stuff = service.getDividends("AAPL", "2019-02-01", "2020-02-01", BuildConfig.API_KEY)

        val thread = Thread {
            val response = stuff?.execute()
            val length = response!!.body()!!.size

            runOnUiThread {
                initDataBinding("$length is")
            }
        }
        thread.start()
    }
}
