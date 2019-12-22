package com.example.store.features.dashboard.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["parentId", "childId"],
    indices = [Index(value = ["parentId", "childId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            entity = ListEntity::class,
            parentColumns = ["id"],
            childColumns = ["parentId"]
        ),
        ForeignKey(
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            entity = ListEntity::class,
            parentColumns = ["id"],
            childColumns = ["childId"]
        )
    ]
)
data class ParentChildEntity(
    val parentId: Int,
    val childId: Int
)