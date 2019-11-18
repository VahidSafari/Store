package com.example.store.features.dashboard.data

import com.example.store.core.api.NetworkHandler
import com.example.store.core.api.Result
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val storeRemoteDataSource: StoreRemoteDataSource,
    private val storeLocalDataSource: StoreLocalDataSource,
    private val networkHandler: NetworkHandler
) {

    suspend fun getStoreInfo(): Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>? {
        var result: Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>? = null
        if (networkHandler.hasNetworkConnection()) {
            when (val response = storeRemoteDataSource.getStoreInformation()) {
                is Result.Success -> {
                    val topSliderItems = response.data.data.slider
                    val bestSellerPieces = response.data.data.best_sellers
                    val mostViewedPieces = response.data.data.most_viewed
                    val specialOfferPieces = response.data.data.special_offers
                    val newestPieces = response.data.data.newest
                    storeLocalDataSource.insertTopSliderItems(
                        topSliderItems.map { it.toTopSliderEntity() }
                    )
                    storeLocalDataSource.insertCategories(
                        listOf(
                            PiecesDto(CategoryEntity(1, "پیشنهاد ویژه"),
                                specialOfferPieces.map { it.toPieceEntity(1) }
                            ), PiecesDto(CategoryEntity(2, "پرفروش"),
                                bestSellerPieces.map { it.toPieceEntity(2) }
                            ), PiecesDto(CategoryEntity(3, "پر بازدید"),
                                mostViewedPieces.map { it.toPieceEntity(3) }
                            ), PiecesDto(CategoryEntity(4, "جدید"),
                                newestPieces.map { it.toPieceEntity(4) }
                            )
                        )
                    )

                    val resultPair = Pair(
                        storeLocalDataSource.getTopSliderItems(),
                        storeLocalDataSource.getAllCategories()
                    )
                    result = Result.Success(resultPair)
                }
                is Result.Error -> {
                    result = Result.Error(response.message)
                }
            }
        } else {
            result = Result.Error("Network problem",
                Pair(
                    storeLocalDataSource.getTopSliderItems(),
                    storeLocalDataSource.getAllCategories()
                )
            )
        }
        return result
    }
}