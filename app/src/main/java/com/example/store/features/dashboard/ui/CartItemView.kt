package com.example.store.features.dashboard.ui

data class CartItemView(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val startPrice: Int,
    val endPrice: Int,
    val off_percent: Int,
    val offPrice: OffPrice,
    var count: Int
)