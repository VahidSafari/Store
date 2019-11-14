package com.example.store.features.dashboard.data

import androidx.room.Embedded
import androidx.room.Relation

class PiecesDto {

    @Embedded
    var categoryEntity: CategoryEntity = CategoryEntity(0,"", listOf())

    @Relation(parentColumn = "id", entityColumn = "id", entity = PieceEntity::class)
    var pieces: List<PieceEntity> = listOf()

}