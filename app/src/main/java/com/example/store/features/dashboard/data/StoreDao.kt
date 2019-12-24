package com.example.store.features.dashboard.data

import androidx.room.*
import com.example.store.features.dashboard.ui.CartItemView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET

@Dao
abstract class StoreDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTopSliderItem(topSliderEntity: TopSliderEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTopSliderItems(topSliderEntities: List<TopSliderEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertPiece(pieceEntity: PieceEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertPieces(pieces: List<PieceEntity>)

    @Query("SELECT * FROM PieceEntity WHERE id=:pieceId")
    abstract suspend fun getPiece(pieceId: Int): PieceEntity?

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

    suspend fun insertCategory(piecesDtos: List<PiecesDto>) {
        piecesDtos.forEach {
            insertCategory(it.categoryEntity)
            insertPieces(it.pieces)
        }
    }

    @Query("INSERT INTO CartEntity(pieceId,count) VALUES(:pieceId,1)")
    abstract fun insertCartItem(pieceId: Int)

    @Query("DELETE FROM CartEntity WHERE pieceId=:pieceId")
    abstract fun removeCartItem(pieceId: Int)

    @Query(
        """
            SELECT ce.pieceId, pe.categoryId, imageUrl, title, startPrice, endPrice, offPercent,
             startOffPrice, endOffPrice, count
            FROM PieceEntity as pe INNER JOIN CartEntity as ce 
            ON pe.id=ce.pieceId
            """
    )
    abstract suspend fun getCartItems(): List<CartItemView>
}