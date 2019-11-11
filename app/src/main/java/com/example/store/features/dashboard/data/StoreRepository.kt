package com.example.store.features.dashboard.data

import javax.inject.Inject

class StoreRepository @Inject constructor(val remoteDataSource: RemoteDataSource) {

    suspend fun getStoreInformation() = remoteDataSource.getStoreInformation()
}