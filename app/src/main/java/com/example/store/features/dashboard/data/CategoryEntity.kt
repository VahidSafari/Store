package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity @JvmOverloads constructor (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title: String,
    @Ignore
    val pieces: List<PieceEntity>? = null
)
