package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopSliderEntity (
    @PrimaryKey
    val id: Int,
    val imageUrl: String
)