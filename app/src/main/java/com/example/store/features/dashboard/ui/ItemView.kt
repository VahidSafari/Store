package com.example.store.features.dashboard.ui

data class ItemView(
    val id: Int,
    val imageUrl: String,
    val name: String,
    val startPrice: Int,
    val endPrice: Int,
    val off_percent: Int,
    val offPrice: OffPrice
)

class OffPrice(
    val startOffPrice:Int,
    val endOffPrice:Int
)