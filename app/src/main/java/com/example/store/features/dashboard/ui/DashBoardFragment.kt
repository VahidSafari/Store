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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.store.R
import com.example.store.core.api.Result
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dash_board.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class DashBoardFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val storeViewModel: StoreViewModel by viewModels {
        storeViewModelFactory
    }


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fetching data and submit to the adapter
        srl_dashboard.setOnRefreshListener(this)
        lifecycleScope.launch {
            storeViewModel.getStoreInfo()
        }

        rv_fragment_dash_board.layoutManager = LinearLayoutManager(context)
        val headerAdapter =
            HeaderAdapter(
                viewLifecycleOwner
            )
        rv_fragment_dash_board.adapter = headerAdapter

        storeViewModel.storeInfo.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    if (result.data.first.isNotEmpty() && result.data.second.isNotEmpty())
                        headerAdapter.submitList(listOf(StoreView(
                            result.data.first.map { it.toTopSliderView() },
                            result.data.second.map { it.toCategoryView() }
                        )))
                }
                is Result.Error -> {
                    if (result.data?.first?.isNotEmpty() == true)
                        result.data.let {
                            headerAdapter.submitList(listOf(StoreView(
                                result.data.first.map { it.toTopSliderView() },
                                result.data.second.map { it.toCategoryView() }
                            )))
                        }
                    srl_dashboard.isRefreshing = false
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    srl_dashboard.isRefreshing = true
                }
            }
        })
    }

    override fun onRefresh() {
        storeViewModel.getStoreInfo()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            srl_dashboard.isRefreshing = false
        }
    }

}