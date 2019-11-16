package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.features.dashboard.ui.TopSliderView

@Entity
data class TopSliderEntity (
    @PrimaryKey
    val id: Int,
    val imageUrl: String
) {
    fun toTopSliderView() = TopSliderView(
        id,
        imageUrl
    )
}