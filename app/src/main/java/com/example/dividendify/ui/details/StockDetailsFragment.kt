package com.example.dividendify.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dividendify.R
import com.example.dividendify.models.CompanyProfile
import com.example.dividendify.models.StockQuote
import com.example.dividendify.ui.home.HomeFragmentDirections
import kotlinx.android.synthetic.main.stock_details_fragment.*

class StockDetailsFragment : Fragment(R.layout.stock_details_fragment) {
    val stockDetailsViewModel: StockDetailsViewModel by viewModels()

    val args: StockDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val companyProfile = args.companyProfile
        stockName.text = companyProfile.name
        stockDetailsViewModel.onCreate(companyProfile.ticker!!)

        val stockQuoteObserver = Observer<StockQuote> { stockQuote ->
            currentPrice.text = stockQuote.currentPrice.toString()
        }

        stockDetailsViewModel.stockQuote.observe(viewLifecycleOwner, stockQuoteObserver)
    }
}