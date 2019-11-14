package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    indices = [Index("categoryId")],
    primaryKeys = ["id","categoryId"],
    foreignKeys = [ForeignKey(
        onDelete = ForeignKey.CASCADE,
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"]
    )]
)
data class PieceEntity (
    val id: Int,
    val title: String,
    val imageUrl: String,
    val startPrice: Int,
    val endPrice: Int,
    val offPrecent: Int,
    val startOffPrice: Int,
    val endOffPrice: Int,

    val categoryId: Int
)