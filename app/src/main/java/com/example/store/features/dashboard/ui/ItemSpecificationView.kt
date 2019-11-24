package com.example.store.features.dashboard.ui

data class ItemSpecificationView (
    val id: Int,
    val title: String,
    val startPrice: Int,
    val endPrice: Int,
    val startDiscountPrice: Int,
    val endDiscountPrice: Int,
    val colors: List<String>,
    val SmallestSize: Int,
    val BiggestSize: Int
)