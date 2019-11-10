package com.example.store.features.dashboard.data

import retrofit2.Response
import retrofit2.http.GET

interface StoreService {
    @GET("/shop/home_page")
    suspend fun getStoreInformation(): Response<StoreResponse>
}