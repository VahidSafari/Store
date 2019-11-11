package com.example.store.core.di.modules

import com.example.store.features.dashboard.data.RemoteDataSource
import com.example.store.features.dashboard.data.StoreRepository
import com.example.store.features.dashboard.data.StoreService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class StoreModule {

//    @Provides
//    @Singleton
//    fun provideRemoteDataSource() = RemoteDataSource()

//    @Provides
//    @Singleton
//    fun provideStoreRepository() = StoreRepository()

    @Provides
    @Singleton
    fun provideStoreService(retrofit: Retrofit) =
        retrofit.create(StoreService::class.java)
}