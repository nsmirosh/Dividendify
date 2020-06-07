package com.example.dividendify.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.dividendify.R
import kotlinx.android.synthetic.main.stock_details_fragment.*

class StockDetailsFragment : Fragment(R.layout.stock_details_fragment) {

    val stockDetailsViewModel: StockDetailsViewModel by viewModels()

    val args: StockDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val stockQuote = args.stockQuote
        stockName.text = stockQuote.currentPrice.toString()
    }
}