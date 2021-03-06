package com.example.store.features.dashboard.data

import com.example.store.core.db.StoreDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class StoreLocalDataSource @Inject constructor(
    val db: StoreDatabase
) {
    suspend fun insertCategories(piecesDtos: List<PiecesDto>) = withContext(Dispatchers.IO) {
        return@withContext try {
            db.getStoreDao().insertCategory(piecesDtos)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getAllCategories(): List<PiecesDto> {
        var result: List<PiecesDto> = listOf()
        withContext(Dispatchers.IO) {
            result = db.getStoreDao().getAllCategories()
        }
        return result
    }

    suspend fun insertTopSliderItems(topSliderItems: List<TopSliderEntity>) =
        withContext(Dispatchers.IO) {
            return@withContext try {
                db.getStoreDao().insertTopSliderItems(topSliderItems)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    suspend fun getPiece(pieceId: Int): PieceEntity? = db.getStoreDao().getPiece(pieceId)

    suspend fun getTopSliderItems(): List<TopSliderEntity> {
        var result: List<TopSliderEntity> = listOf()
        withContext(Dispatchers.IO) {
            result = db.getStoreDao().getTopSliderItems()
        }
        return result
    }

    suspend fun getCartItems() = db.getStoreDao().getCartItems()

    fun insertCartItem(pieceId: Int) =
        try {
            db.getStoreDao().insertCartItem(pieceId)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    fun removeCartItem(pieceId: Int) =
        try {
            db.getStoreDao().removeCartItem(pieceId)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    suspend fun search(searchPhrase: String) = db.getStoreDao().search(searchPhrase)
}