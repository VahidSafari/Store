package com.example.store.features.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.store.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dash_board.*

class DashBoardFragment : Fragment() {

    private lateinit var inflatedView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(tb_dashboard)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.fragment_dash_board, container, false)
        return inflatedView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //top slider
        rv_top_slider.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val topSliderList = listOf(
            TopSliderView(
                1,
                "https://mootanroo.com/sites/default/files/styles/slider_and_teaser/public/slide/5_5.jpg"
            ),
            TopSliderView(
                2,
                "https://mootanroo.com/sites/default/files/styles/slider_and_teaser/public/slide/hue_saturation_2.jpg"
            ),
            TopSliderView(
                3,
                "https://mootanroo.com/sites/default/files/styles/slider_and_teaser/public/slide/63_0.jpg"
            )
        )
        val topSliderAdapter = TopSliderAdapter()
        rv_top_slider.layoutManager = layoutManager
        rv_top_slider.adapter = topSliderAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_top_slider)
        val divider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        rv_top_slider.addItemDecoration(divider)
        topSliderAdapter.submitList(topSliderList)
    }
}