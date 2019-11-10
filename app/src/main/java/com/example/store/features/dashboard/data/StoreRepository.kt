package com.example.store.features.dashboard.data

import com.example.store.MainActivity
import javax.inject.Inject

class StoreRepository {

    @Inject lateinit var remoteDataSource: RemoteDataSource

    init {
        MainActivity.daggerStoreComponent.inject(this)
    }

    suspend fun getStoreInformation() = remoteDataSource.getStoreInformation()
}