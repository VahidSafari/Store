package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.Fts4

@Fts4(contentEntity = PieceEntity::class)
@Entity(tableName = "piecesFts")
data class PieceFts(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val startPrice: String,
    val endPrice: String,
    val offPercent: String,
    val startOffPrice: String,
    val endOffPrice: String
)