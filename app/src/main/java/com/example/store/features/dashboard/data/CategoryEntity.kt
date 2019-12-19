package com.example.store.features.dashboard.data

import androidx.annotation.NonNull
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [Index(value = ["id"], unique = true)]
)
data class CategoryEntity @JvmOverloads constructor (
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id : Int,
    val title: String,
    @Ignore
    val pieces: List<PieceEntity>? = null
)
