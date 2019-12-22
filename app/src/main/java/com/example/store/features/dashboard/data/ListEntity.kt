package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)