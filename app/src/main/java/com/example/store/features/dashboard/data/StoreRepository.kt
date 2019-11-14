package com.example.store.features.dashboard.data

import javax.inject.Inject
import com.example.store.core.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoreRepository @Inject constructor(
    val storeRemoteDataSource: StoreRemoteDataSource,
    val storeLocalDataSource: StoreLocalDataSource
) {

    suspend fun getStoreInfo() : Result<Pair<TopSliderEntity,PiecesDto>>? {
        var result : Result<Pair<TopSliderEntity,PiecesDto>>? = null
        withContext(Dispatchers.IO) {
            val response = storeRemoteDataSource.getStoreInformation()
            when(response) {
                is Result.Success -> {
                    val topSliderItems = response.data.data.slider
                    val bestSellerPieces = response.data.data.best_sellers
                    val mostViewedPieces = response.data.data.most_viewed
                    val specialOfferPieces = response.data.data.special_offers
                    val newestPieces = response.data.data.newest
                    storeLocalDataSource.insertTopSliderItems(
                        topSliderItems.map { it.toTopSliderEntity() }
                    )
                    storeLocalDataSource.insertCategories(listOf(PiecesDto()))
                }
                is Result.Error -> {
                    result =  Result.Error(response.message)
                }
            }
        }
        return result
    }
}