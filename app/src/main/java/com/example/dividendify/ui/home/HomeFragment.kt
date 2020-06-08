package com.example.dividendify.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dividendify.R
import com.example.dividendify.models.CompanyProfile
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {

    lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch_search_button.setOnClickListener {
            viewModel.onSearchClicked(search.text.toString())
        }

        val nameObserver = Observer<CompanyProfile> { companyProfile ->
            view.findNavController()
                .navigate(HomeFragmentDirections.toStockDetailsFragment(companyProfile))
        }

        viewModel.companyProfile.observe(viewLifecycleOwner, nameObserver)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }
}