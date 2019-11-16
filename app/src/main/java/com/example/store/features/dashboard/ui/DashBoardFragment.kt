package com.example.store.features.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.store.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import com.example.store.core.api.Result
import com.example.store.features.dashboard.data.PiecesDto
import com.example.store.features.dashboard.data.StoreResponse
import com.example.store.features.dashboard.data.TopSliderEntity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dash_board.*
import kotlinx.android.synthetic.main.recycler_item_categories.*
import kotlinx.android.synthetic.main.recycler_item_top_slider.*
import javax.inject.Inject

class DashBoardFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val storeViewModel: StoreViewModel by viewModels {
        storeViewModelFactory
    }

//        ViewModelProviders.of(this,storeViewModelFactory)
//        .get(StoreViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(tb_dashboard)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dash_board, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        //top slider
//        rv_top_slider.setHasFixedSize(true)
//        topSliderAdapter = TopSliderAdapter()
//        rv_top_slider.layoutManager = object : LinearLayoutManager(context, HORIZONTAL, true) {
//            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
//                lp?.width = width * 85 / 100
//                lp?.setMargins(12, 12, 12, 12)
//                return super.checkLayoutParams(lp)
//            }
//        }
//        rv_top_slider.adapter = topSliderAdapter
//        val snapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(rv_top_slider)
//        autoScrollTopSlider()
//
//        /*
//         *category recycler view
//         */
//        rv_categories.setHasFixedSize(true)
//
//        rv_categories.layoutManager =
//            LinearLayoutManager(context)
//        categoryAdapter = CategoryAdapter()
//        rv_categories.adapter = categoryAdapter

        //fetching data and submit to the adapter
        srl_dashboard.setOnRefreshListener(this)
        lifecycleScope.launch{
            storeViewModel.getStoreInfo()
        }
        storeViewModel.storeInfo.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Success -> {
                    applyDataToAdapters(it.data)
                }
                is Result.Error -> {
                    srl_dashboard.isRefreshing = false
                }
                is Result.Loading -> {
                    srl_dashboard.isRefreshing = true
                }
            }
        })
    }

    private fun applyDataToAdapters(response: Pair<List<TopSliderEntity>, List<PiecesDto>>) {
        rv_fragment_dash_board.layoutManager = LinearLayoutManager(context)
        val headerAdapter =
            HeaderAdapter(
                viewLifecycleOwner
            )
        headerAdapter.submitList(listOf(StoreView(
            response.first.map { it.toTopSliderView() },
            response.second.map { it.toCategoryView() }
        )))
        rv_fragment_dash_board.adapter = headerAdapter

//        topSliderAdapter.submitList(response.data.toTopSliderViewList())
//        categoryAdapter.submitList(response.data.toCategoryViewList())
    }

    override fun onRefresh() {
        storeViewModel.getStoreInfo()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            srl_dashboard.isRefreshing = false
        }
    }

}