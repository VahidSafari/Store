package com.example.store.features.dashboard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["pieceId"],
    indices = [
        Index(value = ["pieceId"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            entity = PieceEntity::class,
            parentColumns = ["id"],
            childColumns = ["pieceId"]
        )
    ]
)
data class CartEntity(
    @ColumnInfo(name = "pieceId")
    val pieceId: Int,
    var count: Int
)