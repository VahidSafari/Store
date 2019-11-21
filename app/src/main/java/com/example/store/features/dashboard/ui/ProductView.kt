package com.example.store.features.dashboard.ui

data class ProductView(
    val id: Int,
    val name: String,
    val startPrice: Int,
    val endPrice: Int,
    val count: Int
)