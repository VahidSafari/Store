package com.example.store.features.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.store.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val storeViewModel: StoreViewModel by viewModels {
        storeViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        storeViewModel.search("کفش")
        storeViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            if (it == null || it.isEmpty())
                tv_search_result.text = "محصول موردنظر یافت نشد :("
            else if (it.isNotEmpty())
                tv_search_result.text = it[0].title
        })
    }
}
