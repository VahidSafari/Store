package com.example.store.features.dashboard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["pieceId", "categoryId"],
    indices = [
        Index(value = ["pieceId"], unique = true),
        Index(value = ["categoryId"], unique = true)
    ],
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
    @ColumnInfo(name = "pieceId")
    val pieceId: Int,
    @ColumnInfo(name = "categoryId")
    val categoryId: Int,
    var count: Int
)