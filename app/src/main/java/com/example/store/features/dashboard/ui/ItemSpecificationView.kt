package com.example.store.features.dashboard.ui

data class ItemSpecificationView (
    val id: Int,
    val title: String,
    val price: Int,
    val discountPrice: Int,
    val colors: List<String>,
    val SmallestSize: Int,
    val BiggestSize: Int
)