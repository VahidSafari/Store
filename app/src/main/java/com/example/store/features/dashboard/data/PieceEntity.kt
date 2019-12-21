package com.example.store.features.dashboard.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.store.features.dashboard.ui.ItemView
import com.example.store.features.dashboard.ui.OffPrice
import com.google.gson.annotations.SerializedName


@Entity(
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["categoryId"], unique = false)
    ],
    primaryKeys = ["id"],
    foreignKeys = [ForeignKey(
        onDelete = ForeignKey.CASCADE,
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"]
    )]
)
data class PieceEntity(
    @ColumnInfo(name = "id")
    @NonNull
    @SerializedName("id")
    val id: Int,
    val title: String,
    val imageUrl: String,
    val startPrice: Int,
    val endPrice: Int,
    val offPercent: Int,
    val startOffPrice: Int,
    val endOffPrice: Int,
    @ColumnInfo(name = "categoryId")
    @NonNull
    @SerializedName("categoryId")
    val categoryId: Int
) {
    fun toItemView() = ItemView(
        id,
        imageUrl,
        title,
        startPrice,
        endPrice,
        offPercent,
        OffPrice(
            startOffPrice,
            endOffPrice
        )
    )
}