package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.store.R
import com.example.store.core.api.Result
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_dash_board.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class DashBoardFragment : DaggerFragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val storeViewModel: StoreViewModel by viewModels {
        storeViewModelFactory
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

        val actionBarHeight = with(TypedValue().also {
            context?.theme?.resolveAttribute(
                android.R.attr.actionBarSize,
                it,
                true
            )
        }) {
            TypedValue.complexToDimensionPixelSize(this.data, resources.displayMetrics)
        }

        srl_dashboard.setProgressViewOffset(
            true,
            actionBarHeight,
            actionBarHeight + actionBarHeight / 2
        )

        lifecycleScope.launch(Dispatchers.IO) {
            val job = storeViewModel.populateDataBase()
            job.join()
            storeViewModel.getStoreInfo()
        }

        rv_fragment_dash_board.layoutManager = LinearLayoutManager(context)
        val headerAdapter =
            MainPageAdapter(
                viewLifecycleOwner
            ) {
                startActivity(Intent(activity, ItemSpecificationActivity::class.java))
            }
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
        /*headerAdapter.submitList(
            listOf(
                StoreView(
                    listOf(
                        TopSliderView(
                            1,
                            "https://mootanroo.com/sites/default/files/users/user167479/slide-farben-mobile.jpg"
                        ),
                        TopSliderView(
                            2,
                            "https://mootanroo.com/sites/default/files/users/user167479/slide-vichy-mobile.jpg"
                        ),
                        TopSliderView(
                            3,
                            "https://mootanroo.com/sites/default/files/users/user167479/slide-my-mobile.jpg"
                        )
                    ),
                    listOf(
                        CategoryView(
                            1,
                            "پیشنهادهای ویژه",
                            listOf(
                                ItemView(
                                    1,
                                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                                    " کفش عالی",
                                    40000,
                                    20000,
                                    12,
                                    OffPrice(50000, 40000)
                                ),
                                ItemView(
                                    2,
                                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                                    " کفش خوب",
                                    10000,
                                    30000,
                                    12,
                                    OffPrice(60000, 45000)
                                ),
                                ItemView(
                                    3,
                                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                                    " کفش بی نظیر",
                                    40000,
                                    20000,
                                    12,
                                    OffPrice(50000, 40000)
                                )
                            )
                        ),
                        CategoryView(
                            2,
                            "پرفروش ها",
                            listOf(
                                ItemView(
                                    1,
                                    "http://pluspng.com/img-png/shirt-hd-png-dress-shirt-png-hd-png-image-480.png",
                                    " ویلا",
                                    6500000,
                                    10000000,
                                    3,
                                    OffPrice(500000, 400000)
                                ),
                                ItemView(
                                    2,
                                    "https://www.freepngimg.com/thumb/cheese/10-cheese-png-image-thumb.png",
                                    "پنیر تازه",
                                    99000,
                                    120000,
                                    12,
                                    OffPrice(60000, 45000)
                                ),
                                ItemView(
                                    3,
                                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                                    " کفش بی نظیر",
                                    21100,
                                    24000,
                                    12,
                                    OffPrice(23000, 22000)
                                ),
                                ItemView(
                                    4,
                                    "https://demo.accesspressthemes.com/wordpress-plugins/wp-popup-banners-pro/wp-content/uploads/2017/04/shoes.png",
                                    "کفش ارزان",
                                    2000,
                                    3000,
                                    3,
                                    OffPrice(2500, 2200)
                                ),
                                ItemView(
                                    5,
                                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                                    "کفش ورزشی",
                                    40000,
                                    50000,
                                    10,
                                    OffPrice(36000, 45000)
                                ),
                                ItemView(
                                    6,
                                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                                    "کفش زیبا",
                                    53000,
                                    68000,
                                    20,
                                    OffPrice(60000, 58000)
                                )
                            )
                        ),
                        CategoryView(
                            3,
                            "جدیدترین ها",
                            listOf(
                                ItemView(
                                    1,
                                    "https://www.housingpedia.com/wp-content/uploads/house-flip.png",
                                    "خانه ی بزرگ",
                                    400000,
                                    500000,
                                    21,
                                    OffPrice(32000, 30000)
                                ),
                                ItemView(
                                    2,
                                    "https://demo.accesspressthemes.com/wordpress-plugins/wp-popup-banners-pro/wp-content/uploads/2017/04/shoes.png",
                                    "کفش مارک",
                                    912000,
                                    930000,
                                    9,
                                    OffPrice(100000, 80000)
                                ),
                                ItemView(
                                    3,
                                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                                    " کفش بی نظیر",
                                    1200000,
                                    1300000,
                                    1,
                                    OffPrice(130000, 125000)
                                ),
                                ItemView(
                                    4,
                                    "https://demo.accesspressthemes.com/wordpress-plugins/wp-popup-banners-pro/wp-content/uploads/2017/04/shoes.png",
                                    "کفش ارزان",
                                    2000,
                                    3000,
                                    3,
                                    OffPrice(2500, 2200)
                                ),
                                ItemView(
                                    5,
                                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                                    "کفش ورزشی",
                                    40000,
                                    50000,
                                    10,
                                    OffPrice(36000, 45000)
                                )
                            )
                        )
                    )
                )
            )
        )*/
    }

    override fun onRefresh() {
        storeViewModel.getStoreInfo()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            srl_dashboard.isRefreshing = false
        }
    }

}