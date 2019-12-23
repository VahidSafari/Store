package com.example.store.features.dashboard.ui

import com.example.store.features.dashboard.data.ListEntity

data class ListView(
    val id: Int,
    val title: String,
    val type: ListItemType
) {
    fun toListEntity() =
        ListEntity(
            id,
            title,
            type == ListItemType.NESTED
        )
}