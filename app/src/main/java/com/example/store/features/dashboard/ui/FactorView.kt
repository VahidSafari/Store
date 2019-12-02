package com.example.store.features.dashboard.ui

data class FactorView(
    val id:Int,
    val productList: String,
    val totalPrice: Long,
    val state: ProductState
)