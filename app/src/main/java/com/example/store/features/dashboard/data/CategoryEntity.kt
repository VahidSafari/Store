package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.store.features.dashboard.ui.CategoryView

@Entity
data class CategoryEntity @JvmOverloads constructor (
    @PrimaryKey
    val id : Int,
    val title: String,
    @Ignore
    val pieces: List<PieceEntity>? = null
)
