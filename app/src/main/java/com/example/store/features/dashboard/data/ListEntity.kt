package com.example.store.features.dashboard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.store.features.dashboard.ui.ListItemType
import com.example.store.features.dashboard.ui.ListView

@Entity(
    tableName = "list_entity"
)
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "has_child")
    val hasChild: Boolean
) {
    fun toListView() =
        ListView(
            id,
            name,
            if (hasChild) ListItemType.NESTED
            else ListItemType.FLATTEN
        )
}