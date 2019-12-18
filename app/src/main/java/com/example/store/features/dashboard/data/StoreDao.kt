package com.example.store.features.dashboard.data

import androidx.room.*
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

    @Transaction
    @Query("SELECT * FROM CategoryEntity")
    abstract suspend fun getAllCategories(): List<PiecesDto>

    @Transaction
    @Query("SELECT * FROM CategoryEntity WHERE title = :categoryTitle")
    abstract suspend fun getCategory(categoryTitle: String): PiecesDto

    @Query("SELECT * FROM TopSliderEntity")
    abstract suspend fun getTopSliderItems(): List<TopSliderEntity>

    @Query(
        """SELECT * 
        FROM PieceEntity
        WHERE title LIKE :searchPhrase"""
    )
    abstract suspend fun search(searchPhrase: String): List<PieceEntity>?

    suspend fun insertCategory(piecesDtos: List<PiecesDto>) = withContext(Dispatchers.IO) {
        piecesDtos.forEach {
            insertCategory(it.categoryEntity)
            insertPieces(it.pieces)
        }
    }

    @Query("INSERT INTO CartEntity(pieceId,categoryId) VALUES(:pieceId,:categoryId)")
    abstract fun insertCartItem(pieceId: Int, categoryId: Int)

    @Query("SELECT * FROM PieceEntity WHERE id = :pieceId AND categoryId = :categoryId")
    abstract suspend fun getCartEntities(pieceId: Int, categoryId: Int): List<PieceEntity>

    @Delete
    abstract fun removeCartItem(cartEntity: CartEntity)

}