package com.example.store.features.dashboard.ui

data class CartItemView(
    val pieceId: Int,
    val categoryId: Int,
    val imageUrl: String,
    val title: String,
    val startPrice: Int,
    val endPrice: Int,
    val offPercent: Int,
    val startOffPrice: Int,
    val endOffPrice: Int,
    var count: Int
)