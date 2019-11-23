package com.example.store.features.dashboard.data


data class Data(

    val slider: List<Slider>,
    val special_offers: List<DiscountPieceResponse>,
    val best_sellers: List<PieceResponse>,
    val most_viewed: List<PieceResponse>,
    val newest: List<PieceResponse>
)