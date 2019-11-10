package com.example.store.features.dashboard.data

import com.example.store.MainActivity
import javax.inject.Inject
import com.example.store.core.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteDataSource {

    @Inject lateinit var storeService: StoreService

    init {
        MainActivity.daggerStoreComponent.inject(this)
    }

    suspend fun getStoreInformation(): Result<StoreResponse>? {
        var result : Result<StoreResponse>? = null
        withContext(Dispatchers.IO){
            val asyncCall = storeService.getStoreInformation()
            try {
                when(asyncCall.code()){
                    200 -> {
                        asyncCall.body()?.let {
                            result = Result.Success(it)
                        }
                    }
                    else -> {
                        result = Result.Error("Connection error")
                    }
                }
            } catch (exception:Exception){
                exception.printStackTrace()
            }
        }
        return result
    }
}