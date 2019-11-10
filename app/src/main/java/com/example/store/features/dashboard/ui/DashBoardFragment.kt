package com.example.store.features.dashboard.ui

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dash_board.*
import kotlinx.coroutines.*


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
        rv_top_slider.layoutManager = object:LinearLayoutManager(context,HORIZONTAL,true){
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                lp?.width = width * 85 / 100
                lp?.setMargins(12,12,12,12)
                return super.checkLayoutParams(lp)
            }
        }
        rv_top_slider.adapter = topSliderAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_top_slider)
        topSliderAdapter.submitList(topSliderList)
        autoScrollTopSlider()
        //category recycler view
        rv_categories.setHasFixedSize(true)
        val categories = listOf<CategoryView>(
            CategoryView(
                "پرفروش ها",
                listOf(
                    ItemView(
                        0,
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        " کفش خوب",
                        10000,
                        20000,
                        12,
                        140000,
                        160000
                    ),
                    ItemView(
                        0,
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        "کفش خوب",
                        10000,
                        20000,
                        12,
                        140000,
                        160000
                    ),
                    ItemView(
                        0,
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        "کفش خوب",
                        10000,
                        20000,
                        12,
                        140000,
                        160000
                    )
                )
            ),
            CategoryView(
                "کم فروش ها",
                listOf(
                    ItemView(
                        0,
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        "کفش خوب",
                        10000,
                        20000,
                        12,
                        140000,
                        160000
                    ),
                    ItemView(
                        0,
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        "کفش عالی",
                        10000,
                        20000,
                        12,
                        140000,
                        160000
                    ),
                    ItemView(
                        0,
                        "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                        "کفش آشغال",
                        10000,
                        20000,
                        12,
                        140000,
                        160000
                    )
                )
            )
        )
        val categoryAdapter = CategoryAdapter()
        rv_categories.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rv_categories.adapter = categoryAdapter
        rv_categories.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val itemSize = view.width + view.paddingStart + view.paddingEnd
                parent.adapter?.let {
                    val padding = (parent.width - (it.itemCount * itemSize)) / 2
                    outRect.set(padding,0,0,0)
                }
                super.getItemOffsets(outRect, view, parent, state)
            }
        })
        categoryAdapter.submitList(categories)
    }

    private fun autoScrollTopSlider() {
        lifecycleScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT){
            while (isActive){
                var preScrollPosition = (rv_top_slider.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                delay(3000)
                var postScrollPosition = (rv_top_slider.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                if (postScrollPosition != preScrollPosition)
                    continue
                when(postScrollPosition){
                    rv_top_slider.adapter?.itemCount?.minus(1) -> {
                        rv_top_slider.smoothScrollToPosition(0)
                    }
                    else -> {
                        rv_top_slider.smoothScrollToPosition(++postScrollPosition)
                    }
                }
            }
        }
    }
}