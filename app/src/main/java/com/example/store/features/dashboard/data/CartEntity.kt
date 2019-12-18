package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["id","categoryId"],
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.SET_DEFAULT,
            entity = PieceEntity::class,
            parentColumns = ["id"],
            childColumns = ["pieceId"]
        ),
        ForeignKey(
            onDelete = ForeignKey.SET_DEFAULT,
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        )
    ]
)
data class CartEntity(
    val pieceId: Int,
    val categoryId: Int
)