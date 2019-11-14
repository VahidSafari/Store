package com.example.store.features.dashboard.data

import com.example.store.features.dashboard.ui.CategoryView
import com.example.store.features.dashboard.ui.ItemView
import com.example.store.features.dashboard.ui.OffPrice
import com.example.store.features.dashboard.ui.TopSliderView


data class Data (

    val slider : List<Slider>,
    val special_offers : List<DiscountPieceResponse>,
    val best_sellers : List<PieceResponse>,
    val most_viewed : List<PieceResponse>,
    val newest : List<PieceResponse>
) {
    fun toTopSliderViewList() : List<TopSliderView>{
        return slider.map {
            TopSliderView(it.id,it.image)
        }
    }

    fun toCategoryViewList() : List<CategoryView> {
        val categoryList = mutableListOf<CategoryView>()
        categoryList.apply {

            add(
                CategoryView(
                    "پیشنهاد ویژه",
                    special_offers.map {
                        ItemView(
                            it.id,
                            it.image,
                            it.title,
                            it.start_price,
                            it.end_price,
                            it.off_percent,
                            OffPrice(
                                it.start_off_price,
                                it.end_off_price
                            )
                        )
                    }
                )
            )

            add(
                CategoryView(
                    "پرفروش",
                    best_sellers.map {
                        ItemView(
                            it.id,
                            it.image,
                            it.title,
                            it.start_price,
                            it.end_price,
                            0,
                            OffPrice(0,0)
                        )
                    }
                )
            )

            add(
                CategoryView(
                    "پربازدید",
                    most_viewed.map {
                        ItemView(
                            it.id,
                            it.image,
                            it.title,
                            it.start_price,
                            it.end_price,
                            0,
                            OffPrice(0,0)
                        )
                    }
                )
            )

            add(
                CategoryView(
                    "جدید",
                    most_viewed.map {
                        ItemView(
                            it.id,
                            it.image,
                            it.title,
                            it.start_price,
                            it.end_price,
                            0,
                            OffPrice(0,0)
                        )
                    }
                )
            )

        }
        return categoryList
    }
}