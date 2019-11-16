package com.example.store.features.dashboard.data

open class PieceResponse (
    val id : Int,
    val title : String,
    val image : String,
    val start_price : Int,
    val end_price : Int
) {
    open fun toPieceEntity(categoryId: Int) = PieceEntity(
        id,
        title,
        image,
        start_price,
        end_price,
        0,
        0,
        0,
        categoryId
    )
}