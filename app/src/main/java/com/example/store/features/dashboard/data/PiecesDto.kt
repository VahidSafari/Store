package com.example.store.features.dashboard.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.store.features.dashboard.ui.CategoryView

class PiecesDto(
    @Embedded var categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId",
        entity = PieceEntity::class
    ) var pieces: List<PieceEntity>
) {
    fun toCategoryView() = CategoryView(
        categoryEntity.id,
        categoryEntity.title,
        pieces.map { it.toItemView() }
    )
}