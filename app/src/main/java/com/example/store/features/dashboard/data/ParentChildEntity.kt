package com.example.store.features.dashboard.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "parent_child_entity",
    primaryKeys = ["parent_id", "child_id"],
    indices = [Index(value = ["parent_id", "child_id"], unique = true)],
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            entity = ListEntity::class,
            parentColumns = ["id"],
            childColumns = ["parent_id"]
        ),
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            entity = ListEntity::class,
            parentColumns = ["id"],
            childColumns = ["child_id"]
        )
    ]
)
data class ParentChildEntity(
    @ColumnInfo(name = "parent_id")
    val parentId: Int,
    @ColumnInfo(name = "child_id")
    val childId: Int
)