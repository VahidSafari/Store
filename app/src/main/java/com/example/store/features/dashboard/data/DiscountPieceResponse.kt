package com.example.store.features.dashboard.data

class DiscountPieceResponse(
    id : Int,
    title : String,
    image : String,
    start_price : Int,
    end_price : Int,
    val off_percent : Int,
    val start_off_price : Int,
    val end_off_price : Int
) : PieceResponse(
    id,
    title,
    image,
    start_price,
    end_price
) {
    override fun toPieceEntity(categoryId: Int) = PieceEntity(
        id,
        title,
        image,
        start_price,
        end_price,
        off_percent,
        start_off_price,
        end_off_price,
        categoryId
    )
}