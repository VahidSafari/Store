package com.example.store.features.dashboard.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Dao
abstract class StoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTopSliderItem(topSliderEntity: TopSliderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertTopSliderItems(topSliderEntities: List<TopSliderEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPiece(pieceEntity: PieceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPieces(pieces: List<PieceEntity>)

    @Query("SELECT * FROM CategoryEntity")
    abstract suspend fun getAllCategories(): List<PiecesDto>

    @Query("SELECT * FROM CategoryEntity WHERE title = :categoryTitle")
    abstract suspend fun getCategory(categoryTitle: String): PiecesDto

    @Query("SELECT * FROM TopSliderEntity")
    abstract suspend fun getTopSliderItems(): List<TopSliderEntity>

    suspend fun insertCategory(piecesDtos: List<PiecesDto>) = withContext(Dispatchers.IO) {
        piecesDtos.forEach {
            insertCategory(it.categoryEntity)
            insertPieces(it.pieces)
        }
    }

}